package com.jiangxk.base.utils

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast

/**
 * @description com.jiangxk.base.utils
 * @author jiangxk
 * @time 2020-05-13  17:42
 */

private val displayMetrics = Resources.getSystem().displayMetrics

fun Context.toast(message: String?, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, duration).show()


fun Float.dp2px() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, displayMetrics)


object Utils {
}

