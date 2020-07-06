package com.jiangxk.hencoderlearningview.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.jiangxk.hencoderlearningview.R
import com.jiangxk.hencoderlearningview.extension.dp2Px

/**
 * @description com.jiangxk.hencoderlearningview.View
 * @author jiangxk
 * @time 2020-05-28  14:44
 */
class CircleView : View {
    companion object {
        val WIDTH = 100f.dp2Px()
    }

    private val paint: Paint = Paint().apply {
        strokeWidth = 1f
        style = Paint.Style.FILL_AND_STROKE
        color = Color.BLACK
        isAntiAlias = true
    }
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    private val xfermode2 = PorterDuffXfermode(PorterDuff.Mode.DST_OVER)

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        println("CircleView onSizeChanged")
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        println("CircleView onDraw")
        val count = canvas.saveLayer(
            0f, 0f, width.toFloat(), height.toFloat(),
            null
        )
        canvas.drawCircle(width / 2f, height / 2f, WIDTH, paint)
        paint.xfermode = xfermode
        canvas.drawBitmap(
            getAvatar(2 * WIDTH),
            width / 2f - WIDTH,
            height / 2f - WIDTH,
            paint
        )
        paint.xfermode = xfermode2
        canvas.drawCircle(width / 2f, height / 2f, WIDTH + 10f.dp2Px(), paint)
        paint.xfermode = null
        canvas.restoreToCount(count)

    }

    private fun getAvatar(width: Float): Bitmap {
        val options = BitmapFactory.Options()
            .apply {
                inJustDecodeBounds = true
                BitmapFactory.decodeResource(resources, R.drawable.avatar, this)
                inJustDecodeBounds = false
                inDensity = outWidth
                inTargetDensity = width.toInt()
            }

        return BitmapFactory.decodeResource(resources, R.drawable.avatar, options)
    }

}