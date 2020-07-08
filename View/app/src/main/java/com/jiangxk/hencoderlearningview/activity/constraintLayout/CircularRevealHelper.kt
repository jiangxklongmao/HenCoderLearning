package com.jiangxk.hencoderlearningview.activity.constraintLayout

import android.content.Context
import android.util.AttributeSet
import android.view.ViewAnimationUtils
import androidx.constraintlayout.widget.ConstraintHelper
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.hypot

/**
 * @description com.jiangxk.hencoderlearningview.activity.constraintLayout
 * @author jiangxk
 * @time 2020/7/8  9:09 PM
 */
class CircularRevealHelper(context: Context?, attrs: AttributeSet?) :
    ConstraintHelper(context, attrs) {


    override fun updatePostLayout(container: ConstraintLayout) {
        super.updatePostLayout(container)

        referencedIds.forEach {
            val view = container.getViewById(it)
            val radius = hypot(view.width.toDouble(), view.height.toDouble()).toFloat()

            ViewAnimationUtils.createCircularReveal(view, 0, 0, 0f, radius)
                .setDuration(2000L)
                .start()
        }
    }

}