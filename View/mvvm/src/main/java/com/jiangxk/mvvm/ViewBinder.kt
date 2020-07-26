package com.jiangxk.mvvm

import android.text.TextUtils
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged

/**
 * @description com.jiangxk.mvvm
 * @author jiangxk
 * @time 2020/7/26  3:41 PM
 */
class ViewBinder {
    companion object {
        fun bind(editText: EditText, stringAttr: StringAttr) {
            editText.doAfterTextChanged {
                if (!TextUtils.equals(stringAttr.value, it)) {
                    stringAttr.value = it.toString()
                    println("UI数据 通知内存 $it")
                }
            }
            stringAttr.listener = object : StringAttr.OnChangeListener {
                override fun onChange(newValue: String?) {
                    if (!TextUtils.equals(newValue, editText.text)) {
                        editText.setText(newValue)
                        editText.setSelection(newValue?.length ?: 0)
                        println("内存数据 通知UI $newValue")
                    }
                }
            }
        }
    }
}