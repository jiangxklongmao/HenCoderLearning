package com.jiangxk.hencoderlearningview.activity

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.jiangxk.hencoderlearningview.R
import com.jiangxk.hencoderlearningview.view.LabelTextView
import kotlinx.android.synthetic.main.activity_tab_layout.*

/**
 * @description com.jiangxk.hencoderlearningview.activity
 * @author jiangxk
 * @time 2020-06-10  22:39
 */
class LabelLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout)

        for (i in 0..30) {
            labelLayout.addView(
                LabelTextView(this),
                ViewGroup.MarginLayoutParams(
                    ViewGroup.MarginLayoutParams.WRAP_CONTENT,
                    ViewGroup.MarginLayoutParams.WRAP_CONTENT
                )
            )
        }

    }
}