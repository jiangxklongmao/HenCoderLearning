package com.jiangxk.hencoderlearningview.activity.motionLayout

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.jiangxk.hencoderlearningview.R
import com.jiangxk.hencoderlearningview.extension.dp2Px
import kotlinx.android.synthetic.main.activity_constraint_layout.*

/**
 * @description com.jiangxk.hencoderlearningview.activity.motionLayout
 * @author jiangxk
 * @time 2020/7/11  12:25 PM
 */
class MotionLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_layout)



        packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES).activities
            .filter {
                it.name.contains("motionLayout")
            }
            .filterNot {
                it.name == this::class.java.name
            }
            .map {
                Class.forName(it.name)
            }
            .forEach { clazz ->
                linear.addView(AppCompatButton(this).apply {
                    height = 80.dp2Px().toInt()
                    isAllCaps = false
                    text = clazz.simpleName
                    setOnClickListener {
                        startActivity(Intent(this@MotionLayoutActivity, clazz))
                    }
                })
            }

    }
}