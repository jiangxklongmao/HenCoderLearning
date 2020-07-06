package com.jiangxk.hencoderlearningview.view.animation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.jiangxk.hencoderlearningview.R
import com.jiangxk.hencoderlearningview.extension.dp2Px

/**
 * @description com.jiangxk.hencoderlearningview.View.animation
 * @author jiangxk
 * @time 2020-06-03  10:23
 */
class CircleAnimationView : View {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    private val paint = Paint().apply {
        isAntiAlias = true
        color = resources.getColor(R.color.colorAccent)
    }

    private var radius = 50.dp2Px()
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {

        canvas.drawCircle(width / 2f, height / 2f, radius, paint)
    }

}