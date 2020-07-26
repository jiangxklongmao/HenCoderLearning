package com.jiangxk.mvvm

/**
 * @description com.jiangxk.mvvm
 * @author jiangxk
 * @time 2020/7/26  3:45 PM
 */
class StringAttr {
    var value: String? = null
        set(value) {
            field = value
            listener?.onChange(value)
        }
    var listener: OnChangeListener? = null

    interface OnChangeListener {
        fun onChange(newValue: String?)
    }
}