package com.jiangxk.hencoderlearningview.activity.motionLayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout.*
import com.jiangxk.hencoderlearningview.R
import kotlinx.android.synthetic.main.activity_helix_line.*

/**
 * @description com.jiangxk.hencoderlearningview.activity.motionLayout
 * @author jiangxk
 * @time 2020/7/15  6:19 PM
 */
class HelixLineActivity : AppCompatActivity() {

    private var showPath = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_helix_line)

        motionLayout.setOnLongClickListener {
            showPath = !showPath
            if (showPath) {
                motionLayout.setDebugMode(DEBUG_SHOW_PATH)
            } else {
                motionLayout.setDebugMode(DEBUG_SHOW_NONE)
            }
            return@setOnLongClickListener true
        }
    }
}