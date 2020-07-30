package com.jiangxk.hencoderlearningview.genericity

import java.util.*
import kotlin.collections.ArrayList

/**
 * @description com.jiangxk.hencoderlearningview.genericity
 * @author jiangxk
 * @time 2020/7/27  4:16 PM
 */
class ReversableArrayList<T> : ArrayList<T>() {

    fun reverse() {
        Collections.reverse(this)
    }

}