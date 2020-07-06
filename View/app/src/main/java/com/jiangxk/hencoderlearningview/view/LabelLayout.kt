package com.jiangxk.hencoderlearningview.view

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children
import kotlin.math.max
import kotlin.math.min

/**
 * @description com.jiangxk.hencoderlearningview.View
 * @author jiangxk
 * @time 2020-06-10  22:54
 */
class LabelLayout : ViewGroup {
    private val childrenBounds = mutableListOf<Rect>()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthUsed = 0
        var heightUsed = 0
        var lineWidthUsed = 0
        var lineMaxHeight = 0

        /**
         * 前一行 第最后一个子View 序号
         */
        var preLineLastIndex = 0

        /**
         * 前一行 子View 个数
         */
        var preLineCount = 0

        val widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthMeasureSize = MeasureSpec.getSize(widthMeasureSpec)

        for ((index, child) in children.withIndex()) {

            measureChildWithMargins(
                child,
                widthMeasureSpec,
                0,
                heightMeasureSpec,
                heightUsed
            )

            if (widthMeasureMode != MeasureSpec.UNSPECIFIED && lineWidthUsed + child.measuredWidth > widthMeasureSize) {
                heightUsed += lineMaxHeight
                lineWidthUsed = 0
                lineMaxHeight = 0

                preLineLastIndex = preLineCount - 1

                measureChildWithMargins(
                    child,
                    widthMeasureSpec,
                    0,
                    heightMeasureSpec,
                    heightUsed
                )
            }

            preLineCount += 1

            if (index >= childrenBounds.size) {
                childrenBounds.add(Rect())
            }

            val childBounds = childrenBounds[index]

            //上一行第一个序号
            val firstIndex = preLineLastIndex - preLineCount
            var currentIndex = firstIndex

            if (firstIndex >= 0) {
                var lastUsableWidth =
                    childrenBounds[firstIndex].right - lineWidthUsed

                var lastUsableBottom = childrenBounds[firstIndex].bottom

                while (child.measuredWidth > lastUsableWidth) {
                    currentIndex += 1
                    lastUsableWidth = childrenBounds[currentIndex].right - lineWidthUsed
                    lastUsableBottom = min(lastUsableBottom, childrenBounds[currentIndex].bottom)
                }

                childBounds.set(
                    lineWidthUsed,
                    lastUsableBottom,
                    lineWidthUsed + child.measuredWidth,
                    lastUsableBottom + child.measuredHeight
                )

            } else {
                childBounds.set(
                    lineWidthUsed,
                    heightUsed,
                    lineWidthUsed + child.measuredWidth,
                    heightUsed + child.measuredHeight
                )
            }
            lineWidthUsed += child.measuredWidth
            widthUsed = max(widthUsed, lineWidthUsed)
            lineMaxHeight = max(lineMaxHeight, child.measuredHeight)
        }

        val selfWidth = widthUsed
        val selfHeight = heightUsed + lineMaxHeight

        setMeasuredDimension(selfWidth, selfHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for ((index, child) in children.withIndex()) {
            val childBounds = childrenBounds[index]
            child.layout(childBounds.left, childBounds.top, childBounds.right, childBounds.bottom)
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

}