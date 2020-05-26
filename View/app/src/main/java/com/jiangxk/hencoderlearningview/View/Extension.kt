package com.jiangxk.hencoderlearningview.View

import android.content.res.Resources
import android.util.TypedValue

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