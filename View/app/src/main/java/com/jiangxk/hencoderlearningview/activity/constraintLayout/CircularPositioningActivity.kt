package com.jiangxk.hencoderlearningview.activity.constraintLayout

import android.animation.ValueAnimator
import android.animation.ValueAnimator.INFINITE
import android.os.Bundle
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.jiangxk.hencoderlearningview.R
import kotlinx.android.synthetic.main.activity_circular_positioning.*

/**
 * @description com.jiangxk.hencoderlearningview.activity.constraintLayout
 * @author jiangxk
 * @time 2020/7/7  9:54 PM
 */
class CircularPositioningActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circular_positioning)

        val earthAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 3000L
            repeatCount = INFINITE
            interpolator = LinearInterpolator()
            addUpdateListener {
                val params = earth.layoutParams as ConstraintLayout.LayoutParams
                params.circleAngle = it.animatedFraction * 360
                earth.requestLayout()
            }
        }

        val moonAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 2000L
            repeatCount = INFINITE
            interpolator = LinearInterpolator()
            addUpdateListener {
                val params = moon.layoutParams as ConstraintLayout.LayoutParams
                params.circleAngle = 90 + it.animatedFraction * 360
                moon.requestLayout()
            }
        }

        sun.setOnClickListener {
            earthAnimator.start()
            moonAnimator.start()
        }


    }
}