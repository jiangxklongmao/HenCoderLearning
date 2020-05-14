package com.jiangxk.base.utils

import android.content.Context
import com.jiangxk.base.BaseApplication
import com.jiangxk.base.R

/**
 * @description com.jiangxk.base.utils
 * @author jiangxk
 * @time 2020-05-13  21:48
 */
class CacheUtils {
    companion object {
        private val context = BaseApplication.currentApplication()
        private val SP =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

        /**
         * 保存
         * @param key String
         * @param value String
         */
        fun save(key: String, value: String) {
            SP.edit().putString(key, value).apply()
        }

        fun get(key: String) = SP.getString(key, null)
    }
}