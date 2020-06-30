package com.jiangxk.hencoderlearningview.View

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.OverScroller
import androidx.core.view.children
import kotlin.math.abs

/**
 * @description com.jiangxk.hencoderlearningview.View
 * @author jiangxk
 * @time 2020/6/24  10:52 AM
 */
class SimplePager : ViewGroup {

    private val maxVelocity = ViewConfiguration.get(context).scaledMaximumFlingVelocity.toFloat()
    private val minVelocity = ViewConfiguration.get(context).scaledMinimumFlingVelocity.toFloat()
    private val paingSlop = ViewConfiguration.get(context).scaledPagingTouchSlop.toFloat()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var downX = 0f
    private var downY = 0f
    private var currentScrollX = 0
    private var scrolling = false
    private var currentPage = 0

    var OnPageChangeListener: OnPageChangeListener? = null

    private val overScroller = OverScroller(context)
    private val velocityTracker = VelocityTracker.obtain()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var leftUsed = 0

        for (child in children) {
            child.layout(leftUsed, 0, leftUsed + width, height)
            leftUsed += child.width
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (ev.actionMasked == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear()
        }
        velocityTracker.addMovement(ev)

        var result = false
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                scrolling = false
                downX = ev.x
                downY = ev.y
                currentScrollX = scrollX
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = downX - ev.x
                if (abs(dx) > paingSlop) {
                    scrolling = true
                    parent.requestDisallowInterceptTouchEvent(true)
                    result = true
                }
            }
        }
        return result
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.actionMasked == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear()
        }

        velocityTracker.addMovement(event)
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
                currentScrollX = scrollX
            }
            MotionEvent.ACTION_MOVE -> {
                val offsetX = event.x - downX
                val offsetY = event.y - downY


                var scrollOffset = currentScrollX - offsetX.toInt()
                if (scrollOffset <= -width) {
                    scrollOffset = 0
                } else if (scrollOffset > width * childCount) {
                    scrollOffset = width * childCount
                }
                scrollTo(scrollOffset, 0)
            }
            MotionEvent.ACTION_UP -> {
                currentScrollX = scrollX
                velocityTracker.computeCurrentVelocity(1000, maxVelocity)
                val velocityX = velocityTracker.xVelocity

                val offsetX = event.x - downX
                val offsetY = event.y - downY

                var targetPage = when {
                    abs(velocityX) < minVelocity -> {
                        when {
                            abs(offsetX) < width / 2 -> currentPage
                            offsetX < 0 -> {
                                currentPage + 1
                            }
                            else -> {
                                currentPage - 1
                            }
                        }
                    }
                    else -> {
                        if (offsetX < 0) currentPage + 1 else currentPage - 1
                    }
                }

                targetPage = when {
                    targetPage < 0 -> {
                        0
                    }
                    targetPage > childCount - 1 -> {
                        childCount - 1
                    }
                    else -> targetPage
                }

                val scrollDistance =
                    when {
                        targetPage == currentPage -> offsetX.toInt()
                        targetPage < currentPage -> {
                            -currentScrollX % width
                        }
                        else -> {
                            width - currentScrollX % width
                        }
                    }
                currentPage = targetPage

                overScroller.startScroll(currentScrollX, 0, scrollDistance, 0)
                postInvalidateOnAnimation()
            }
        }
        return true
    }

    override fun computeScroll() {

        if (overScroller.computeScrollOffset()) {
            scrollTo(overScroller.currX, overScroller.currY)
            postInvalidateOnAnimation()
        } else {
            OnPageChangeListener?.onChanged(currentPage)
        }
    }
}

interface OnPageChangeListener {
    fun onChanged(position: Int)
}
