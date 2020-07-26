package com.jiangxk.mvvm

import android.widget.EditText

/**
 * @description com.jiangxk.mvvm
 * @author jiangxk
 * @time 2020/7/26  2:51 PM
 */
class ViewModel(data1View: EditText, data2View: EditText) {

    var data1 = StringAttr()
    var data2 = StringAttr()

    init {
        ViewBinder.bind(data1View, data1)
        ViewBinder.bind(data2View, data2)
    }

    fun init() {
        val data = DataCenter.getData()
        data1.value = data[0]
        data2.value = data[1]
    }
}