package com.jiangxk.hencoderlearningview.view

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.NestedScrollingChild3
import androidx.core.view.NestedScrollingChildHelper
import androidx.core.view.ViewCompat
import com.jiangxk.hencoderlearningview.extension.dp2Px
import com.jiangxk.hencoderlearningview.extension.getAvatar
import kotlin.math.max
import kotlin.math.min

/**
 * @description com.jiangxk.hencoderlearningview.view
 * @author jiangxk
 * @time 2020/7/6  3:30 PM
 */
class NestedScalableImageView : View, NestedScrollingChild3 {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    companion object {
        val IMAGE_SIZE = 300.dp2Px()
        const val EXTRA_SCALE_FACTOR = 1.5f
    }

    private val paint = Paint().apply {
        isAntiAlias = true
    }

    private val bitmap = getAvatar(context, IMAGE_SIZE)

    private var originalOffsetX = 0f
    private var originalOffsetY = 0f

    private var offsetX = 0f
    private var offsetY = 0f

    private var smallScale = 0f
    private var bigScale = 0f

    private var isBig = false

//    private var scaleFraction = 0f
//        set(value) {
//            field = value
//            invalidate()
//        }

    private var currentScale = smallScale
        set(value) {
            field = value
            invalidate()
        }

    private val scaleAnimation =
        ObjectAnimator.ofFloat(this, "currentScale", smallScale, bigScale).apply {
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {

                }

                override fun onAnimationEnd(animation: Animator?) {
                    setFloatValues(smallScale, bigScale)
                }

                override fun onAnimationCancel(animation: Animator?) {

                }

                override fun onAnimationStart(animation: Animator?) {

                }

            })
        }

    private val scaleImageGestureListener = ScalableImageGestureListener()
    private val gestureDetector = GestureDetectorCompat(context, scaleImageGestureListener)

    private val scalableImageScaleGestureDetectorListener =
        ScalableImageScaleGestureDetectorListener()
    private val scaleGestureDetector =
        ScaleGestureDetector(context, scalableImageScaleGestureDetectorListener)

    private val scroller = OverScroller(context)

    private val scalableImageFlingRunner = ScalableImageFlingRunner()

    private val childHelper = NestedScrollingChildHelper(this).apply {
        isNestedScrollingEnabled = true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val result = scaleGestureDetector.onTouchEvent(event)
        if (!scaleGestureDetector.isInProgress) {
            gestureDetector.onTouchEvent(event)
            childHelper.startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL)
        }
        return result
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        originalOffsetX = (width - IMAGE_SIZE) / 2f
        originalOffsetY = (height - IMAGE_SIZE) / 2f

        if (bitmap.width / bitmap.height.toFloat() > width / height.toFloat()) {
            smallScale = width / bitmap.width.toFloat()
            bigScale = height / bitmap.height.toFloat() * EXTRA_SCALE_FACTOR
        } else {
            smallScale = height / bitmap.height.toFloat()
            bigScale = width / bitmap.width.toFloat() * EXTRA_SCALE_FACTOR
        }

        currentScale = smallScale

        scaleAnimation.setFloatValues(smallScale, bigScale)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val scaleFraction = (currentScale - smallScale) / (bigScale - smallScale)

        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction)
        canvas.scale(currentScale, currentScale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint)

    }

    private fun fixOffset() {
        offsetX = min(offsetX, (bitmap.width * bigScale - width) / 2)
        offsetX = max(offsetX, -(bitmap.width * bigScale - width) / 2)

        offsetY = min(offsetY, (bitmap.height * bigScale - height) / 2)
        offsetY = max(offsetY, -(bitmap.height * bigScale - height) / 2)
    }

    private fun fixOffsets(): Int {
        val rawOffsetY = offsetY
        val maxOffsetY = (bitmap.height * bigScale - height) / 2

        offsetX = min(offsetX, (bitmap.width * bigScale - width) / 2)
        offsetX = max(offsetX, -(bitmap.width * bigScale - width) / 2)

        offsetY = min(offsetY, maxOffsetY)
        offsetY = max(offsetY, -maxOffsetY)

        return when {
            rawOffsetY < -maxOffsetY -> (-maxOffsetY - rawOffsetY).toInt()
            rawOffsetY > maxOffsetY -> (maxOffsetY - rawOffsetY).toInt()
            else -> 0
        }

    }

    inner class ScalableImageGestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            if (isBig) {
                scroller.fling(
                    offsetX.toInt(),
                    offsetY.toInt(),
                    velocityX.toInt(),
                    velocityY.toInt(),
                    (-(bitmap.width * bigScale - width) / 2).toInt(),
                    ((bitmap.width * bigScale - width) / 2).toInt(),
                    (-(bitmap.height * bigScale - height) / 2).toInt(),
                    ((bitmap.height * bigScale - height) / 2).toInt(),
//                    50.dp2Px().toInt(), 50.dp2Px().toInt()
                    0, 0
                )

                postOnAnimation(scalableImageFlingRunner)
            }
            return true
        }

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            if (isBig) {
                offsetX -= distanceX
                offsetY -= distanceY

                val unconsumed = fixOffsets()
                if (unconsumed != 0) {
                    childHelper.dispatchNestedScroll(0, 0, 0, unconsumed, null)
                } else {
                    invalidate()
                }
            }else{
                childHelper.hasNestedScrollingParent()
            }

            return true
        }

        override fun onDoubleTap(e: MotionEvent): Boolean {
            isBig = !isBig
            if (isBig) {

                offsetX = (e.x - width / 2f) * (1 - bigScale / smallScale)
                offsetY = (e.y - height / 2f) * (1 - bigScale / smallScale)
                fixOffsets()
                scaleAnimation.start()
            } else {
                scaleAnimation.reverse()
            }
            return true
        }
    }

    inner class ScalableImageFlingRunner : Runnable {
        override fun run() {
            if (scroller.computeScrollOffset()) {
                offsetX = scroller.currX.toFloat()
                offsetY = scroller.currY.toFloat()
                invalidate()
                ViewCompat.postOnAnimation(this@NestedScalableImageView, this)
            }
        }

    }

    inner class ScalableImageScaleGestureDetectorListener :
        ScaleGestureDetector.SimpleOnScaleGestureListener() {

        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            offsetX = (detector.focusX - width / 2f) * (1 - bigScale / smallScale)
            offsetY = (detector.focusY - height / 2f) * (1 - bigScale / smallScale)
            return true
        }

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            val tempCurrentScale = currentScale * detector.scaleFactor

            val scaleFraction = (tempCurrentScale - smallScale) / (bigScale - smallScale)
            scaleAnimation.setFloatValues(scaleFraction, bigScale)
            return when {
                tempCurrentScale <= smallScale -> {
                    isBig = false
                    scaleAnimation.setFloatValues(smallScale, bigScale)
                    false
                }
                tempCurrentScale >= bigScale -> {
                    isBig = true
                    scaleAnimation.setFloatValues(smallScale, bigScale)
                    false
                }
                else -> {
                    println("detector.scaleFactor = ${detector.scaleFactor}")
                    currentScale *= detector.scaleFactor
                    if (detector.scaleFactor > 1f) {
                        scaleAnimation.setFloatValues(currentScale, bigScale)
                    } else {
                        scaleAnimation.setFloatValues(smallScale, currentScale)
                    }
                    true
                }
            }
        }
    }

    override fun dispatchNestedScroll(
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        offsetInWindow: IntArray?,
        type: Int,
        consumed: IntArray
    ) {
        childHelper.dispatchNestedScroll(
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            offsetInWindow,
            type,
            consumed
        )
    }

    override fun dispatchNestedScroll(
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        offsetInWindow: IntArray?,
        type: Int
    ): Boolean {
        return childHelper.dispatchNestedScroll(
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            offsetInWindow,
            type
        )
    }

    override fun dispatchNestedPreScroll(
        dx: Int,
        dy: Int,
        consumed: IntArray?,
        offsetInWindow: IntArray?,
        type: Int
    ): Boolean {
        return childHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type)
    }

    override fun stopNestedScroll(type: Int) {
        return childHelper.stopNestedScroll(type)
    }

    override fun hasNestedScrollingParent(type: Int): Boolean {
        return childHelper.hasNestedScrollingParent(type)
    }

    override fun startNestedScroll(axes: Int, type: Int): Boolean {
        return childHelper.startNestedScroll(axes, type)
    }
}