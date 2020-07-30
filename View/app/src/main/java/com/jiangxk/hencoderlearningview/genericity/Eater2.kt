package com.jiangxk.hencoderlearningview.genericity

/**
 * @description com.jiangxk.hencoderlearningview.genericity
 * @author jiangxk
 * @time 2020/7/27  4:18 PM
 */
interface Eater2<T : Food> {

    fun eat(food: T)

}