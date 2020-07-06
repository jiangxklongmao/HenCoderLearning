package com.jiangxk.hencoderlearningview.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.jiangxk.hencoderlearningview.R
import com.jiangxk.hencoderlearningview.extension.dp2Px

/**
 * @description com.jiangxk.hencoderlearningview.View
 * @author jiangxk
 * @time 2020-06-01  11:23
 */
class GeometricTransformationView : View {
    companion object {
        val AVATAR_MARGIN = 100.dp2Px()
        val AVATAR_SIZE = 200.dp2Px()
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    private var topFlipRotate = 0f
        set(value) {
            field = value
            invalidate()
        }
    private var bottomFlipRotate = 0f
        set(value) {
            field = value
            invalidate()
        }
    private var angleOfInclination = 0f
        set(value) {
            field = value
            invalidate()
        }

    private val paint = Paint().apply {
        isAntiAlias = true
        strokeWidth = 1f
    }
    private val bitmap = getAvatar(AVATAR_SIZE)

    private val camera = Camera()

    init {
        camera.setLocation(0f, 0f, -5 * resources.displayMetrics.density)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)



        canvas.save()
        canvas.translate((width / 2f), (height / 2f))
        canvas.rotate(-angleOfInclination)
        camera.save()
        camera.rotateX(topFlipRotate)
        camera.applyToCanvas(canvas)
        camera.restore()
        canvas.clipRect(
            2 * -AVATAR_SIZE / 2,
            2 * -AVATAR_SIZE / 2,
            2 * AVATAR_SIZE / 2,
            0f
        )
        canvas.rotate(angleOfInclination)
        canvas.translate(-(width / 2f), -(height / 2f))
        canvas.drawBitmap(bitmap, width / 2 - AVATAR_SIZE / 2, height / 2 - AVATAR_SIZE / 2, paint)
        canvas.restore()


        canvas.save()
        canvas.translate((width / 2f), (height / 2f))
        canvas.rotate(-angleOfInclination)
        camera.save()
        camera.rotateX(bottomFlipRotate)
        camera.applyToCanvas(canvas)
        camera.restore()
        canvas.clipRect(
            2 * -AVATAR_SIZE / 2,
            0f,
            2 * AVATAR_SIZE / 2,
            2 * AVATAR_SIZE / 2
        )
        canvas.rotate(angleOfInclination)
        canvas.translate(-(width / 2f), -(height / 2f))
        canvas.drawBitmap(bitmap, width / 2 - AVATAR_SIZE / 2, height / 2 - AVATAR_SIZE / 2, paint)
        canvas.restore()
    }


    private fun getAvatar(width: Float): Bitmap {
        val options = BitmapFactory.Options()
            .apply {
                inJustDecodeBounds = true
                BitmapFactory.decodeResource(resources, R.drawable.lengtu, this)
                inJustDecodeBounds = false
                inDensity = outWidth
                inScaled = true
                inTargetDensity = width.toInt()
            }

        return BitmapFactory.decodeResource(resources, R.drawable.lengtu, options)
    }

}