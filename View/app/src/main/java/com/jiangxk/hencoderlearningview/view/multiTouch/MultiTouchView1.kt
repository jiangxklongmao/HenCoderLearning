package com.jiangxk.hencoderlearningview.view.multiTouch

import android.content.Context
import android.graphics.Canvas
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
class MultiTouchView1 : View {
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

    private var trackingPointerId = 0

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(avatarBitmap, offsetX, offsetY, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                trackingPointerId = event.getPointerId(0)

                downX = event.x
                downY = event.y

                originalOffsetX = offsetX
                originalOffsetY = offsetY

            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                trackingPointerId = event.getPointerId(event.actionIndex)

                downX = event.getX(event.actionIndex)
                downY = event.getY(event.actionIndex)

                originalOffsetX = offsetX
                originalOffsetY = offsetY
            }
            MotionEvent.ACTION_MOVE -> {
                offsetX =
                    event.getX(event.findPointerIndex(trackingPointerId)) - downX + originalOffsetX
                offsetY =
                    event.getY(event.findPointerIndex(trackingPointerId)) - downY + originalOffsetY

                invalidate()
            }
            MotionEvent.ACTION_POINTER_UP -> {
                val actionIndex = event.actionIndex
                val pointerId = event.getPointerId(event.actionIndex)
                if (trackingPointerId == pointerId) {
                    val newIndex = if (actionIndex == event.pointerCount - 1) {
                        event.pointerCount - 2
                    } else {
                        event.pointerCount - 1
                    }
                    trackingPointerId = event.findPointerIndex(newIndex)
                    downX = event.getX(newIndex)
                    downY = event.getY(newIndex)

                    originalOffsetX = offsetX
                    originalOffsetY = offsetY
                }
            }
        }

        return true
    }

}