package com.jiangxk.hencoderlearningview.view.drag

import android.content.Context
import android.util.AttributeSet
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children

/**
 * @description com.jiangxk.hencoderlearningview.View.drag
 * @author jiangxk
 * @time 2020/7/2  2:28 PM
 */
class DragListenerGridView : ViewGroup {
    companion object {
        const val COLUMNS = 2
        const val ROWS = 3
    }

    private var dragListener: OnDragListener = DragListener()
    private var draggedView: View? = null
    private var orderedChildren: MutableList<View> = ArrayList()

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
        val childWidth = width / COLUMNS
        val childHeight = height / ROWS
        for ((index, child) in children.withIndex()) {
            childLeft = index % 2 * childWidth
            childTop = index / 2 * childHeight
            child.layout(0, 0, childWidth, childHeight)
            child.translationX = childLeft.toFloat()
            child.translationY = childTop.toFloat()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        for (child in children) {
            orderedChildren.add(child) // 初始化位置
            child.setOnLongClickListener { v ->
                draggedView = v
                v.startDrag(null, DragShadowBuilder(v), v, 0)
                false
            }
            child.setOnDragListener(dragListener)
        }
    }

    private inner class DragListener : OnDragListener {


        override fun onDrag(v: View, event: DragEvent): Boolean {
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> if (event.localState === v) {
                    v.visibility = View.INVISIBLE
                }
                DragEvent.ACTION_DRAG_ENTERED -> if (event.localState !== v) {
                    sort(v)
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                }
                DragEvent.ACTION_DRAG_ENDED -> if (event.localState === v) {
                    v.visibility = View.VISIBLE
                }
            }


            return true
        }

    }

    private fun sort(targetView: View) {
        var draggedIndex = -1
        var targetIndex = -1
        for ((index, child) in orderedChildren.withIndex()) {
            if (targetView === child) {
                targetIndex = index
            } else if (draggedView === child) {
                draggedIndex = index
            }
        }
        orderedChildren.removeAt(draggedIndex)
        orderedChildren.add(targetIndex, draggedView!!)
        var childLeft: Int
        var childTop: Int
        val childWidth = width / COLUMNS
        val childHeight = height / ROWS
        for ((index, child) in orderedChildren.withIndex()) {
            childLeft = index % 2 * childWidth
            childTop = index / 2 * childHeight
            child.animate()
                .translationX(childLeft.toFloat())
                .translationY(childTop.toFloat())
                .duration = 150
        }
    }
}