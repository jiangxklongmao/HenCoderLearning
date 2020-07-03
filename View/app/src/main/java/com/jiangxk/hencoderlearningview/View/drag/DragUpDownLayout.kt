package com.jiangxk.hencoderlearningview.View.drag

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.customview.widget.ViewDragHelper
import com.jiangxk.hencoderlearningview.extension.dp2Px
import kotlinx.android.synthetic.main.activity_drag_up_down.view.*
import kotlin.math.abs

/**
 * @description com.jiangxk.hencoderlearningview.View.drag
 * @author jiangxk
 * @time 2020/7/2  5:10 PM
 */
class DragUpDownLayout : LinearLayout {

    private val dragCallback = DragCallback()
    private val dragHelper = ViewDragHelper.create(this, dragCallback)
    private val minFlingVelocity = ViewConfiguration.get(context).scaledMinimumFlingVelocity

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return dragHelper.shouldInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        dragHelper.processTouchEvent(event)
        return true
    }

    override fun computeScroll() {
        if (dragHelper.continueSettling(true)) {
            postInvalidateOnAnimation()
        }
    }

    private inner class DragCallback : ViewDragHelper.Callback() {
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return child === draggedView
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top
        }

        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            return 0
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            if (abs(yvel) > minFlingVelocity) {
                if (yvel > 0) {
                    dragHelper.settleCapturedViewAt(0, 450.dp2Px().toInt())
                } else {
                    dragHelper.settleCapturedViewAt(0, 0)
                }
            } else {
                if (releasedChild.top < height - releasedChild.bottom) {
                    dragHelper.settleCapturedViewAt(0, 0)
                } else {
                    dragHelper.settleCapturedViewAt(0, 450.dp2Px().toInt())
                }
            }
            postInvalidateOnAnimation()
        }
    }

}