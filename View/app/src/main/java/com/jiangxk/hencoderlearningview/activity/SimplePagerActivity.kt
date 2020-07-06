package com.jiangxk.hencoderlearningview.activity

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.jiangxk.hencoderlearningview.R
import com.jiangxk.hencoderlearningview.view.OnPageChangeListener
import com.jiangxk.hencoderlearningview.view.SimplePager
import com.jiangxk.hencoderlearningview.extension.dp2Px

/**
 * @description com.jiangxk.hencoderlearningview.activity
 * @author jiangxk
 * @time 2020/6/24  11:14 AM
 */
class SimplePagerActivity : AppCompatActivity() {

    private val colors = listOf(
        Color.parseColor("#FF5722"), Color.parseColor("#03A9F4"),
        Color.parseColor("#4CAF50"), Color.parseColor("#FFEB3B"), Color.parseColor("#607D8B")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_pager)


        val simplePager = findViewById<SimplePager>(R.id.simple_pager)

        simplePager.OnPageChangeListener = object : OnPageChangeListener {
            override fun onChanged(position: Int) {
                println("positon = $position")
            }

        }



        for (i in 0..4) {
            val textView = TextView(this).apply {
                setTextColor(Color.WHITE)
                setBackgroundColor(colors[i])
                text = "${i + 1}"
                gravity = Gravity.CENTER
                textSize = 30.dp2Px()
            }
            simplePager.addView(
                textView,
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
        }

    }
}