package com.jiangxk.hencoderlearningview.extension

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.TypedValue
import com.jiangxk.hencoderlearningview.R

/**
 * @description com.jiangxk.hencoderlearningview.View
 * @author jiangxk
 * @time 2020-05-25  22:50
 */
fun Float.dp2Px(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )
}

fun Int.dp2Px(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )
}

fun getAvatar(context: Context, width: Float): Bitmap {
    val options = BitmapFactory.Options()
        .apply {
            inJustDecodeBounds = true
            BitmapFactory.decodeResource(context.resources, R.drawable.lengtu, this)
            inJustDecodeBounds = false
            inDensity = outWidth
            inScaled = true
            inTargetDensity = width.toInt()
        }

    return BitmapFactory.decodeResource(context.resources, R.drawable.lengtu, options)
}