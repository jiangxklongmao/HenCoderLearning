package com.jiangxk.hencoderlearningview.View.multiTouch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.jiangxk.hencoderlearningview.extension.dp2Px
import com.jiangxk.hencoderlearningview.extension.getAvatar

/**
 * @description com.jiangxk.hencoderlearningview.View.multiTouch
 * @author jiangxk
 * @time 2020/6/22  2:18 PM
 */
class MultiTouchView2 : View {
    companion object {
        val IMAGE_SIZE = 200.dp2Px()
    }

    private val avatarBitmap = getAvatar(context, IMAGE_SIZE)
    private val paint = Paint().apply {
        isAntiAlias = true
    }

    private var offsetX = 0f
    private var offsetY = 0f

    private var downX = 0f
    private var downY = 0f

    private var originalOffsetX = 0f
    private var originalOffsetY = 0f


    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.parseColor("#000000"))
        canvas.drawBitmap(avatarBitmap, offsetX, offsetY, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var focusX = 0f
        var focusY = 0f
        for (i in 0 until event.pointerCount) {
            if (event.actionMasked == MotionEvent.ACTION_POINTER_UP) {
                if (i != event.actionIndex) {
                    focusX += event.getX(i)
                    focusY += event.getY(i)
                }
            } else {
                focusX += event.getX(i)
                focusY += event.getY(i)
            }
        }

        if (event.actionMasked == MotionEvent.ACTION_POINTER_UP) {
            focusX /= event.pointerCount - 1
            focusY /= event.pointerCount - 1
        } else {
            focusX /= event.pointerCount
            focusY /= event.pointerCount
        }

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN, MotionEvent.ACTION_POINTER_UP -> {
                downX = focusX
                downY = focusY

                originalOffsetX = offsetX
                originalOffsetY = offsetY
            }
            MotionEvent.ACTION_MOVE -> {
                offsetX =
                    focusX - downX + originalOffsetX
                offsetY =
                    focusY - downY + originalOffsetY
            }
        }
        invalidate()
        return true
    }

}