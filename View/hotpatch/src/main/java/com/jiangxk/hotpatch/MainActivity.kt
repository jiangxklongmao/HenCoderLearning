package com.jiangxk.hotpatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dalvik.system.BaseDexClassLoader
import dalvik.system.DexClassLoader
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.lang.reflect.Method
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showTitle.setOnClickListener {
            original()
        }

        hotFix.setOnClickListener {
            hotFix()
        }
        removeFix.setOnClickListener {
            removeFix()
        }
        killSelf.setOnClickListener {
            killSelf()
        }
    }

    private fun original() {
        text.text = Title().getTitle()
    }

    private fun hotFix() {

        val apk = File("$cacheDir/hotfix.dex")
        if (!apk.exists()) {
            assets.open("hotfix.dex").copyTo(FileOutputStream("$cacheDir/hotfix.dex"))
        }


//        val originalLoader = classLoader
//        val classLoader = DexClassLoader(apk.path, cacheDir.path, null, null)
//
//
//        val loaderClass = BaseDexClassLoader::class.java
//        val pathListFiled = loaderClass.getDeclaredField("pathList")
//        pathListFiled.isAccessible = true
//
//        val pathListAny = pathListFiled.get(classLoader)
//
//        val pathListClass = pathListAny.javaClass
//        val dexElementsField = pathListClass.getDeclaredField("dexElements")
//        dexElementsField.isAccessible = true
//
//        val dexElementsAny = dexElementsField.get(pathListAny)
//
//        val originalPathListAny = pathListFiled.get(originalLoader)
//
//
//        dexElementsField.set(originalPathListAny, dexElementsAny)

    }

    private fun removeFix() {
        val dex = File("$cacheDir/hotfix.dex")
        if (dex.exists()) {
            dex.delete()
        }
    }

    private fun killSelf() {
        exitProcess(0)
    }
}
