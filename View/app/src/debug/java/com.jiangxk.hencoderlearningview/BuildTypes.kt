package com.jiangxk.hencoderlearningview

import android.app.Activity
import android.view.View
import android.view.ViewGroup

/**
 * @description com.jiangxk.hencoderlearningview.activity
 * @author jiangxk
 * @time 2020/7/22  11:18 PM
 */

fun Activity.drawBadge() {
    val badge = View(this)
    badge.setBackgroundColor(android.graphics.Color.GREEN)
    val decorView = window.decorView as ViewGroup
    decorView.addView(badge, 200, 200)
}