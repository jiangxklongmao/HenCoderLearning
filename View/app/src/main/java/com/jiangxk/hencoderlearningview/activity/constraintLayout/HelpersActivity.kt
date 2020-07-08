package com.jiangxk.hencoderlearningview.activity.constraintLayout

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jiangxk.hencoderlearningview.R
import kotlinx.android.synthetic.main.activity_helpers.*

/**
 * @description com.jiangxk.hencoderlearningview.activity.constraintLayout
 * @author jiangxk
 * @time 2020/7/8  4:54 PM
 */
class HelpersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_helpers)

        button.setOnClickListener {
            group.visibility = if (group.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }

    }
}