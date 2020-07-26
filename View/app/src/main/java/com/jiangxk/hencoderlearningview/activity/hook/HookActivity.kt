package com.jiangxk.hencoderlearningview.activity.hook

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jiangxk.hencoderlearningview.R
import dalvik.system.DexClassLoader
import kotlinx.android.synthetic.main.activity_hook.*
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.lang.Exception
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * @description com.jiangxk.hencoderlearningview.activity.hook
 * @author jiangxk
 * @time 2020/7/3  5:26 PM
 */
class HookActivity : AppCompatActivity() {
    companion object {
        const val TAG = "Hook"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hook)


        btn_hook.setOnClickListener {
            Toast.makeText(this, "点击Hook", Toast.LENGTH_SHORT).show()
//            reflect()
            classLoader()
        }
    }


    private fun reflect() {

        val classUtils = Class.forName("com.jiangxk.pluginable.utils.Utils")

        val constructorMethod = classUtils.declaredConstructors[0]
        constructorMethod.isAccessible = true
        val utils: Any = constructorMethod.newInstance()
        val shoutMethod: Method = classUtils.getDeclaredMethod("shout")
        shoutMethod.isAccessible = true
        shoutMethod.invoke(utils)

    }

    private fun classLoader() {

        //复制到缓存中
        val apk = File("$cacheDir/plugin.apk")
        val source = assets.open("apk/plugin.apk")
        source.copyTo(FileOutputStream(apk))


        val classLoader = DexClassLoader(apk.path, cacheDir.path, null, null)
        val classUtils = classLoader.loadClass("com.jiangxk.plugin.Utils")

        val constructorMethod = classUtils.declaredConstructors[0]
        constructorMethod.isAccessible = true
        val utils: Any = constructorMethod.newInstance()
        val shoutMethod: Method = classUtils.getDeclaredMethod("shout")
        shoutMethod.isAccessible = true
        shoutMethod.invoke(utils)

    }


    private fun hookOnClickListener(view: View) {
        try {
            val getListenerInfo = View::class.java.getDeclaredMethod("getListenerInfo")
            getListenerInfo.isAccessible = true
            val listenerInfo = getListenerInfo.invoke(view)

            val listenerClazz = Class.forName("android.view.View\$ListenerInfo")
            val mOnClickListener = listenerClazz.getDeclaredField("mOnClickListener")
            mOnClickListener.isAccessible = true

            val originOnClickListener =
                mOnClickListener.get(listenerInfo) as? View.OnClickListener
            val hookedClickListenerProxy = HookedClickListenerProxy(originOnClickListener)

            mOnClickListener.set(listenerInfo, hookedClickListenerProxy)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    private fun test() {
        val notificationManager =
            this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(this, "channelId")
        } else {
            Notification.Builder(this)
        }
        val bundle = Bundle().apply {
            putString("test", "test")
        }
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.addExtras(bundle)
        notificationManager.notify(1, builder.build())
    }

    private fun hookNotificationManager(context: Context) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val getService = NotificationManager::class.java.getDeclaredMethod("getService")
        getService.isAccessible = true

        //第一步 获得系统的 service
        val originService = getService.invoke(notificationManager)
        val iNotificationManagerClass = Class.forName("android.app.INotificationManager")

        //第二部 动态代理

        val newProxyInstance = Proxy.newProxyInstance(
            context.classLoader,
            arrayOf(iNotificationManagerClass)
        ) { proxy, method, args ->
            println("$TAG  invoke()  method: $method")
            val methodName = method.name
            println("$TAG invoke:name = $methodName")

            for (arg in args) {
                println("invoke arg = $arg")
            }

            Toast.makeText(context.applicationContext, "", Toast.LENGTH_SHORT).show()

            return@newProxyInstance method.invoke(originService, args)
        }


        val sService = notificationManager.javaClass.getDeclaredField("sService")
        sService.isAccessible = true

        sService.set(notificationManager, newProxyInstance)
    }


    private inner class HookedClickListenerProxy : View.OnClickListener {
        private var origin: View.OnClickListener?

        constructor(origin: View.OnClickListener?) {
            this.origin = origin
        }

        override fun onClick(v: View) {
            println("Hook Click Listener")
            origin?.onClick(v)
        }

    }

}