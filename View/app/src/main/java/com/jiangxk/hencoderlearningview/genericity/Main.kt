package com.jiangxk.hencoderlearningview.genericity

/**
 * @description com.jiangxk.hencoderlearningview.genericity
 * @author jiangxk
 * @time 2020/7/27  4:12 PM
 */
class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val a: AppleArrayList<Apple> = AppleArrayList()
            a.add(Apple())


        }
    }
}