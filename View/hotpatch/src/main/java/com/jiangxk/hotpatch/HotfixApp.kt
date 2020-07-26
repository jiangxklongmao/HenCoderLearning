package com.jiangxk.hotpatch

import android.app.Application
import android.content.Context
import dalvik.system.BaseDexClassLoader
import dalvik.system.DexClassLoader
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.lang.reflect.Array.getLength

/**
 * @description com.jiangxk.hotpatch
 * @author jiangxk
 * @time 2020/7/25  11:38 PM
 */
public class HotfixApp : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        hotfix()
    }


    private fun hotfix() {
        try {
            val apk = File("$cacheDir/hotfix.dex")

//            if (!apk.exists()) {
//                assets.open("hotfix.dex").copyTo(FileOutputStream("$cacheDir/hotfix.dex"))
//            }

            val originalLoader = classLoader
            val classLoader = DexClassLoader(apk.path, cacheDir.path, null, null)


            val baseClassLoader = BaseDexClassLoader::class.java
            val pathListFiled = baseClassLoader.getDeclaredField("pathList")
            pathListFiled.isAccessible = true

            val pathListAny = pathListFiled.get(classLoader)
            val pathListClass = pathListAny.javaClass
            val dexElementsField = pathListClass.getDeclaredField("dexElements")
            dexElementsField.isAccessible = true
            val dexElementsAny = dexElementsField.get(pathListAny)

            val originalPathListAny = pathListFiled.get(originalLoader)
            val originalDexElementsAny = dexElementsField.get(originalPathListAny)


            val newLength = java.lang.reflect.Array.getLength(dexElementsAny)
            val oldLength = java.lang.reflect.Array.getLength(originalDexElementsAny)

            val concatDexElements = java.lang.reflect.Array.newInstance(
                dexElementsAny.javaClass.componentType,
                newLength + oldLength
            )
            for (i in 0 until newLength) {
                java.lang.reflect.Array.set(
                    concatDexElements,
                    i,
                    java.lang.reflect.Array.get(dexElementsAny, i)
                )
            }

            for (i in 0 until oldLength) {
                java.lang.reflect.Array.set(
                    concatDexElements,
                    newLength + i,
                    java.lang.reflect.Array.get(originalDexElementsAny, i)
                )
            }

            dexElementsField.set(originalPathListAny, concatDexElements)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}