package com.jiangxk.hencoderlearningview.View.animation

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
 * @time 2020-06-03  11:30
 */
class GlobeMotionAnimationView : View {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    private val radius = 10.dp2Px()

    private var globeX = 0f
        set(value) {
            field = value
            invalidate()
        }
    var globeY = 0f
        set(value) {
            field = value
            invalidate()
        }

    private val paint = Paint().apply {
        isAntiAlias = true
        color = resources.getColor(R.color.colorAccent)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawCircle(radius + globeX, radius + globeY, radius, paint)

    }

}