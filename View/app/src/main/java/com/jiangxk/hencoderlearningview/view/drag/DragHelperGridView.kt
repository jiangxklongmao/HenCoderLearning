package com.jiangxk.hencoderlearningview.view.drag

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.customview.widget.ViewDragHelper

/**
 * @description com.jiangxk.hencoderlearningview.View.drag
 * @author jiangxk
 * @time 2020/7/1  10:50 AM
 */
class DragHelperGridView : ViewGroup {

    companion object {
        const val COLUMNS = 2
        const val ROWS = 3
    }

    private var draCallback = DragCallback()

    private var draggedView: View? = null
    private var orderedChildren: MutableList<View> = ArrayList()

    private var dragHelper = ViewDragHelper.create(this, draCallback)
    private var isAnimation = false

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    init {
        isChildrenDrawingOrderEnabled = true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val specWidth = MeasureSpec.getSize(widthMeasureSpec)
        val specHeight = MeasureSpec.getSize(heightMeasureSpec)
        val childWidth = specWidth / COLUMNS
        val childHeight = specHeight / ROWS

        measureChildren(
            MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY)
        )

        setMeasuredDimension(specWidth, specHeight)
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft: Int
        var childTop: Int
        var childWidth = width / COLUMNS
        var childHeight = height / ROWS

        for ((index, child) in children.withIndex()) {
            childLeft = index % 2 * childWidth
            childTop = index / 2 * childHeight
            child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight)
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        orderedChildren.addAll(children)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        if (isAnimation) {
            return false
        }

        return dragHelper.shouldInterceptTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!isAnimation) {
            dragHelper.processTouchEvent(event)
        }
        return true
    }

    override fun computeScroll() {
        if (dragHelper.continueSettling(true)) {
            isAnimation = true
            postInvalidateOnAnimation()
        } else {
            isAnimation = false
        }
    }


    private inner class DragCallback : ViewDragHelper.Callback() {
        var capturedLeft = 0
        var capturedTop = 0

        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return true
        }

        override fun onViewDragStateChanged(state: Int) {
            if (state == ViewDragHelper.STATE_IDLE) {
                val capturedView = dragHelper.capturedView
                if (capturedView != null) {
                    capturedView.elevation--
                }
            }
        }

        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            return left
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top
        }

        override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
            capturedChild.elevation = 1f
            capturedLeft = capturedChild.left
            capturedTop = capturedChild.top
        }

        override fun onViewPositionChanged(
            changedView: View,
            left: Int,
            top: Int,
            dx: Int,
            dy: Int
        ) {
            println("onViewPositionChanged : left = $left  top = $top dx = $dx dy = $dy")
//            sort(changedView)

        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            dragHelper.settleCapturedViewAt(capturedLeft, capturedTop)
            isAnimation = true
            postInvalidateOnAnimation()
        }

    }

    private fun sort(draggedView: View) {
        var draggedIndex = -1
        var targetIndex = -1
        for ((index, child) in orderedChildren.withIndex()) {
            if (draggedView === child) {
                draggedIndex = index
            }

            val focusX = (draggedView.right + draggedView.left) / 2
            val focusY = (draggedView.top + draggedView.bottom) / 2

            if (focusX > child.left && focusX < child.right &&
                focusY > child.top && focusY < child.bottom
            ) {
                targetIndex = index
            }
        }
//        orderedChildren.removeAt(draggedIndex)
//        orderedChildren.add(targetIndex, draggedView!!)

        var childLeft: Int
        var childTop: Int
        val childWidth = width / COLUMNS
        val childHeight = height / ROWS
        if (targetIndex > draggedIndex) {
            for (index in (draggedIndex + 1)..targetIndex) {

                if (index % 2 == 0) {
                    childLeft = childWidth
                    childTop = -childHeight
                } else {
                    childLeft = -childWidth
                    childTop = 0
                }

                val child = orderedChildren[index]
                child.animate()
                    .translationX(childLeft.toFloat())
                    .translationY(childTop.toFloat())
                    .duration = 150
            }
            val targetView = orderedChildren[targetIndex]
            draggedView.left = targetView.left
            draggedView.top = targetView.top
        }

//        var childLeft: Int
//        var childTop: Int
//        val childWidth = width / COLUMNS
//        val childHeight = height / ROWS
//        for ((index, child) in orderedChildren.withIndex()) {
//            childLeft = index % 2 * childWidth
//            childTop = index / 2 * childHeight
//            child.animate()
//                .translationX(childLeft.toFloat())
//                .translationY(childTop.toFloat())
//                .duration = 150
//        }
    }

}