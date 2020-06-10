package com.jiangxk.hencoderlearningview.View

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import com.jiangxk.hencoderlearningview.R
import com.jiangxk.hencoderlearningview.extension.dp2Px

/**
 * @description com.jiangxk.hencoderlearningview.View
 * @author jiangxk
 * @time 2020-06-09  19:49
 */

private val TEXT_SIZE = 12.dp2Px()
private val TEXT_MARGIN = 8.dp2Px()

private val HORIZONTAL_OFFSET = 5.dp2Px()
private val VERTICAL_OFFSET = 24.dp2Px()
private val EXTRA_VERTICAL_OFFSET = 25.dp2Px()


class MaterialEditText(context: Context, attrs: AttributeSet?) :
    AppCompatEditText(context, attrs) {

    private val paint = Paint().apply {
        isAntiAlias = true
        textSize = TEXT_SIZE
    }

    private var floatingLabelShown = false

    var useFloatingLabel = false
        set(value) {
            if (field != value) {
                field = value
                if (field) {
                    setPadding(
                        paddingLeft,
                        (paddingTop + TEXT_SIZE + TEXT_MARGIN).toInt(),
                        paddingRight,
                        paddingBottom
                    )
                } else {
                    setPadding(
                        paddingLeft,
                        (paddingTop - TEXT_SIZE - TEXT_MARGIN).toInt(),
                        paddingRight,
                        paddingBottom
                    )
                }
                invalidate()
            }
        }

    var floatingLabelFraction = 0f
        set(value) {
            field = value
            invalidate()
        }


    private val animator by lazy {
        ObjectAnimator.ofFloat(this, "floatingLabelFraction", 0f, 1f)
    }

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText)



        useFloatingLabel =
            typeArray.getBoolean(R.styleable.MaterialEditText_useFloatingLabel, true)
        typeArray.recycle()
    }


    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        if (floatingLabelShown && text.isNullOrEmpty()) {
            floatingLabelShown = false
            animator.reverse()
        } else if (!floatingLabelShown && !text.isNullOrEmpty()) {
            floatingLabelShown = true
            animator.start()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (useFloatingLabel) {
            paint.alpha = (floatingLabelFraction * 0xff).toInt()

            val currentVerticalValue =
                VERTICAL_OFFSET + EXTRA_VERTICAL_OFFSET * (1 - floatingLabelFraction)
            canvas.drawText(hint.toString(), HORIZONTAL_OFFSET, currentVerticalValue, paint)
        }
    }

}