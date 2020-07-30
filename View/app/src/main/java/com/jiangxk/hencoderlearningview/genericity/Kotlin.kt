package com.jiangxk.hencoderlearningview.genericity

/**
 * @description com.jiangxk.hencoderlearningview.genericity
 * @author jiangxk
 * @time 2020/7/28  5:18 PM
 */
interface KotlinOut<out T> {
    fun getT(): T
}

interface KotlinIn<in T> {
    fun setT(t: T)
}

fun main(args: Array<String>) {

    val out: KotlinOut<Any> = object : KotlinOut<Int> {
        override fun getT(): Int {
            return 1
        }

    }

    val kin: KotlinIn<Int> = object : KotlinIn<Any> {
        override fun setT(t: Any) {

        }
    }
}