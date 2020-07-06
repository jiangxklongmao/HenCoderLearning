package com.jiangxk.hencoderlearningview.view.multiTouch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View
import com.jiangxk.hencoderlearningview.extension.dp2Px
import com.jiangxk.hencoderlearningview.extension.getAvatar

/**
 * @description com.jiangxk.hencoderlearningview.View.multiTouch
 * @author jiangxk
 * @time 2020/6/22  2:18 PM
 */
class MultiTouchView3 : View {
    companion object {
        val IMAGE_SIZE = 200.dp2Px()
    }

    private val avatarBitmap = getAvatar(context, IMAGE_SIZE)
    private val paint = Paint().apply {
        isAntiAlias = true
        strokeWidth = 5.dp2Px()
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }

    private val pathSparseArray = SparseArray<Path>()


    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    override fun onDraw(canvas: Canvas) {
        for (index in 0 until pathSparseArray.size()) {
            val path = pathSparseArray.valueAt(index)
            canvas.drawPath(path, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                val path = Path()
                path.moveTo(event.getX(event.actionIndex), event.getY(event.actionIndex))
                pathSparseArray.append(event.getPointerId(event.actionIndex), path)
            }
            MotionEvent.ACTION_MOVE -> {
                for (index in 0 until event.pointerCount) {
                    val pointerId = event.getPointerId(index)
                    val path = pathSparseArray.get(pointerId)
                    path.lineTo(event.getX(index), event.getY(index))
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_CANCEL -> {
                val pointerId = event.getPointerId(event.actionIndex)
                pathSparseArray.remove(pointerId)
            }
        }
        invalidate()
        return true
    }

//    override fun onTouchEvent(event: MotionEvent): Boolean {
//        when (event.actionMasked) {
//            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
//                val path = Path()
//                path.moveTo(event.getX(event.actionIndex), event.getY(event.actionIndex))
//                pathSparseArray.append(event.getPointerId(event.actionIndex), path)
//            }
//            MotionEvent.ACTION_MOVE -> {
//                for (index in 0 until event.pointerCount) {
//                    val pointerId = event.getPointerId(index)
//                    val path = pathSparseArray.get(pointerId)
//                    path.lineTo(event.getX(index), event.getY(index))
//                }
//            }
//            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_CANCEL -> {
//                val pointerId = event.getPointerId(event.actionIndex)
//                pathSparseArray.remove(pointerId)
//            }
//        }
//        invalidate()
//        return true
//    }

}