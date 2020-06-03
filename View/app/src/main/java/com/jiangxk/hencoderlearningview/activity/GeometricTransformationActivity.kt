package com.jiangxk.hencoderlearningview.activity

import android.animation.ObjectAnimator
import android.animation.ValueAnimator.INFINITE
import android.animation.ValueAnimator.REVERSE
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationSet
import androidx.appcompat.app.AppCompatActivity
import com.jiangxk.hencoderlearningview.R
import com.jiangxk.hencoderlearningview.View.GeometricTransformationView
import com.jiangxk.hencoderlearningview.extension.dp2Px

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