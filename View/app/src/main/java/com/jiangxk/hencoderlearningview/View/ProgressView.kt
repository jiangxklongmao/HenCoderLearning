package com.jiangxk.hencoderlearningview.View

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.jiangxk.hencoderlearningview.extension.dp2Px

/**
 * @description com.jiangxk.hencoderlearningview.View
 * @author jiangxk
 * @time 2020-05-29  11:46
 */
class ProgressView : View {
    companion object {
        val RING_RADUIS = 150.dp2Px()
        val RING_WIDTH = 20.dp2Px()
        val CIRCLE_COLOR = Color.parseColor("#90A4AE")
        val HIGHLIGHT_COLOR = Color.parseColor("#FF4081")
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    private val paint = Paint().apply {
        isAntiAlias = true
    }

    private val textPaint = Paint().apply {
        textSize = 100.dp2Px()
        color = HIGHLIGHT_COLOR
        textAlign = Paint.Align.CENTER
    }

    private val rect = Rect()

    private val text = "agbjap"

    override fun onDraw(canvas: Canvas) {
        paint.apply {
            strokeWidth = RING_WIDTH
            color = CIRCLE_COLOR
            style = Paint.Style.STROKE
        }

        canvas.drawCircle(width / 2f, height / 2f, RING_RADUIS, paint)

        paint.apply {
            color = HIGHLIGHT_COLOR
            strokeCap = Paint.Cap.ROUND
        }

        canvas.drawArc(
            width / 2f - RING_RADUIS,
            height / 2f - RING_RADUIS,
            width / 2 + RING_RADUIS,
            height / 2 + RING_RADUIS,
            -90f,
            240f,
            false,
            paint
        )

        textPaint.getTextBounds(text, 0, text.length, rect)

        canvas.drawText(text, width / 2f, height / 2f - (rect.bottom + rect.top) / 2, textPaint)

    }

}