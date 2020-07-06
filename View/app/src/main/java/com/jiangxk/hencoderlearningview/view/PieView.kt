package com.jiangxk.hencoderlearningview.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.jiangxk.hencoderlearningview.extension.dp2Px
import kotlin.math.*

/**
 * @description com.jiangxk.hencoderlearningview.View
 * @author jiangxk
 * @time 2020-05-26  22:34
 */
class PieView : View {
    private val paint: Paint = Paint().apply {
        strokeWidth = 3f.dp2Px()
        isAntiAlias = true
    }

    private var touchIndex = -1

    private val angles = floatArrayOf(60f, 30f, 120f, 90f, 60f)
    /**
     * 开始角度
     */
    private var startAngles = arrayListOf<Float>()
    private val colors = listOf(
        Color.parseColor("#FF5722"), Color.parseColor("#03A9F4"),
        Color.parseColor("#4CAF50"), Color.parseColor("#FFEB3B"), Color.parseColor("#607D8B")
    )

    companion object {
        val RADIUS = 150f.dp2Px()
        val OFFSET_LENGTH = 20f.dp2Px()
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                touchIndex = findTouchIndex(event.x, event.y)
            }
            MotionEvent.ACTION_MOVE -> {
                touchIndex = findTouchIndex(event.x, event.y)
            }
            MotionEvent.ACTION_UP -> {
                touchIndex = -1
            }
            MotionEvent.ACTION_CANCEL -> {
                touchIndex = -1
            }
        }
        invalidate()
        return true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

    }

    override fun onDraw(canvas: Canvas) {
        startAngles.clear()

        //画扇形
        var used = 0f
        for ((index, angle) in angles.withIndex()) {
            paint.color = colors[index]

            startAngles.add(used)

            val radians = Math.toRadians((angle / 2 + used).toDouble()).toFloat()

            if (touchIndex == index) {
                canvas.save()
                canvas.translate(
                    OFFSET_LENGTH * cos(radians),
                    OFFSET_LENGTH * sin(radians)
                )
            }


            canvas.drawArc(
                width / 2f - RADIUS, height / 2f - RADIUS, width / 2f + RADIUS,
                height / 2f + RADIUS, used, angle, true, paint
            )
            used += angle

            if (touchIndex == index) {
                canvas.restore()
            }
        }

    }


    private fun findTouchIndex(touchX: Float, touchY: Float): Int {
        val distance = getDistance(touchX, touchY, width / 2f, height / 2f)
        if (distance > RADIUS) {
            return -1
        }

        when (getQuadrant(touchX, touchY)) {
            1 -> {
                val touchRadians = asin(((touchY - height / 2f) / distance).toDouble()).toFloat()
                return findAngleIndex(touchRadians)
            }
            2 -> {
                val touchRadians =
                    asin(((touchY - height / 2f) / distance).toDouble()) + Math.PI / 2
                return findAngleIndex(touchRadians.toFloat())
            }
            3 -> {
                val touchRadians =
                    asin(((height / 2f - touchY) / distance).toDouble()) + Math.PI
                return findAngleIndex(touchRadians.toFloat())
            }
            4 -> {
                val touchRadians =
                    2 * Math.PI - asin(((height / 2f - touchY) / distance).toDouble())
                return findAngleIndex(touchRadians.toFloat())
            }
        }
        return -1

    }

    /**
     * 距离公式
     * @param x1 Float
     * @param y1 Float
     * @param x2 Float
     * @param y2 Float
     * @return Float
     */
    private fun getDistance(x1: Float, y1: Float, x2: Float, y2: Float): Float {
        return sqrt((x1 - x2).toDouble().pow(2) + (y1 - y2).toDouble().pow(2)).toFloat()
    }

    /**
     * 求相对于圆心的象限
     * @param x Float
     * @param y Float
     * @return Int
     */
    private fun getQuadrant(x: Float, y: Float): Int {
        val relativeX = x - width / 2
        val relativeY = y - height / 2
        return when {
            relativeX >= 0 && relativeY >= 0 -> 1
            relativeX < 0 && relativeY >= 0 -> 2
            relativeX < 0 && relativeY < 0 -> 3
            relativeX >= 0 && relativeY < 0 -> 4
            else -> 1
        }
    }

    /**
     * 获取弧度
     * @param angle Float
     * @return Float
     */
    private fun getRadians(angle: Float): Float {
        return Math.toRadians(angle.toDouble()).toFloat()
    }

    /**
     * 查找对应的角度序号
     * @param radians Float
     * @return Int
     */
    private fun findAngleIndex(radians: Float): Int {
        for ((index, angle) in angles.withIndex()) {
            if (getRadians(startAngles[index]) <= radians && getRadians(startAngles[index] + angle) >= radians) {
                return index
            }
        }
        return -1
    }

}