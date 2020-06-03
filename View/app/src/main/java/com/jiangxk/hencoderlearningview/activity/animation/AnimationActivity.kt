package com.jiangxk.hencoderlearningview.activity.animation

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jiangxk.hencoderlearningview.R
import com.jiangxk.hencoderlearningview.View.animation.CircleAnimationView
import com.jiangxk.hencoderlearningview.View.animation.GlobeMotionAnimationView
import com.jiangxk.hencoderlearningview.View.animation.TextAnimationView
import com.jiangxk.hencoderlearningview.extension.dp2Px
import kotlinx.android.synthetic.main.activity_animation.*
import kotlin.math.ceil
import kotlin.math.round

/**
 * @description com.jiangxk.hencoderlearningview.activity.animation
 * @author jiangxk
 * @time 2020-06-03  10:27
 */
class AnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
        initView()
    }

    lateinit var objectAnimator: ObjectAnimator

    private fun initView() {

//        val caView = findViewById<CircleAnimationView>(R.id.caView)
//        ObjectAnimator.ofFloat(caView, "radius", 50.dp2Px(), 150.dp2Px())
//            .apply {
//                startDelay = 1000
//                duration = 2000
//                repeatMode = ValueAnimator.REVERSE
//                repeatCount = ValueAnimator.INFINITE
//                start()
//            }


//        val g = 9.8f * 80
//        val gmaView = findViewById<GlobeMotionAnimationView>(R.id.gmaView)
//        ObjectAnimator.ofFloat(gmaView, "globeX", 0.dp2Px(), 300.dp2Px())
//            .apply {
//                startDelay = 1000
//                duration = 1000
//                repeatMode = ValueAnimator.RESTART
//                repeatCount = ValueAnimator.INFINITE
//                addUpdateListener {
//                    val fraction = it.animatedFraction
//                    gmaView.globeY = 1 / 2f * g * fraction * fraction * 2 * 2
//                }
//                start()
//            }


        val taView = findViewById<TextAnimationView>(R.id.taView)

        objectAnimator = ObjectAnimator.ofObject(
            taView,
            "text",
            TypeEvaluator<String> { fraction, startValue, endValue ->
                val startIndex = taView.provinces.indexOf(startValue)
                val endIndex = taView.provinces.indexOf(endValue)
                val index = startIndex +
                        round((fraction * (endIndex - startIndex))).toInt()

                taView.provinces[index]
            },
            "北京市", "澳门特别行政区"
        ).apply {
            startDelay = 1000
            duration = 5000
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
            start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        objectAnimator.cancel()
    }
}