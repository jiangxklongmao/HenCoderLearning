package com.jiangxk.hencoderlearningview

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.jiangxk.hencoderlearningview.View.DashBoardView

/**
 * @description com.jiangxk.hencoderlearningview
 * @author jiangxk
 * @time 2020-05-27  17:43
 */
class DashBoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        val dashBoardView = findViewById<DashBoardView>(R.id.dashBoardView)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    dashBoardView.velocityValue = progress.toFloat()
                }
            }

        })

    }
}