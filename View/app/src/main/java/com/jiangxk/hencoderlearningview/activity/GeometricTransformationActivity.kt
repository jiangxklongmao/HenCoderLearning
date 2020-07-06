package com.jiangxk.hencoderlearningview.activity

import android.animation.ObjectAnimator
import android.animation.ValueAnimator.INFINITE
import android.animation.ValueAnimator.REVERSE
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jiangxk.hencoderlearningview.R
import com.jiangxk.hencoderlearningview.view.GeometricTransformationView

/**
 * @description com.jiangxk.hencoderlearningview.activity
 * @author jiangxk
 * @time 2020-06-01  11:23
 */
class GeometricTransformationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_geometric_transformation)
        initView()
    }

    private fun initView() {
        val geometricTransformationView = findViewById<GeometricTransformationView>(R.id.gtView)

        val topFlipAnimation =
            ObjectAnimator.ofFloat(geometricTransformationView, "topFlipRotate", 0f, -60f)
                .apply {
                    startDelay = 1000
                    duration = 2000
                    repeatMode = REVERSE
                    repeatCount = INFINITE
                    start()
                }
        val bottomFlipAnimation =
            ObjectAnimator.ofFloat(geometricTransformationView, "bottomFlipRotate", 0f, 60f)
                .apply {
                    startDelay = 1000
                    duration = 2000
                    repeatMode = REVERSE
                    repeatCount = INFINITE
                    start()
                }
        val angleOfInclinationAnimation =
            ObjectAnimator.ofFloat(geometricTransformationView, "angleOfInclination", 0f, 360f)
                .apply {
                    startDelay = 1000
                    duration = 2000
                    repeatMode = REVERSE
                    repeatCount = INFINITE
                    start()
                }

    }

}