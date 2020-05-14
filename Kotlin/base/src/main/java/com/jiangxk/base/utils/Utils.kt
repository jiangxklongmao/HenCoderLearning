package com.jiangxk.base.utils

import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import com.jiangxk.base.BaseApplication

/**
 * @description com.jiangxk.base.utils
 * @author jiangxk
 * @time 2020-05-13  17:42
 */

class Utils {
    companion object {
        private val displayMetrics = Resources.getSystem().displayMetrics

        fun dp2px(dp: Float) =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)

        fun toast(message: String?) {
            toast(message, Toast.LENGTH_LONG)
        }

        fun toast(message: String?, duration: Int) {
            Toast.makeText(BaseApplication.currentApplication(), message, duration).show()
        }
    }
}