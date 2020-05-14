package com.jiangxk.base

import android.app.Application
import android.content.Context

/**
 * @description com.jiangxk.base
 * @author jiangxk
 * @time 2020-05-13  18:02
 */
class BaseApplication : Application() {
    companion object {
        private lateinit var currentApplication: Context

        fun currentApplication() = currentApplication
    }


    override fun onCreate() {
        super.onCreate()
        currentApplication = this
    }
}