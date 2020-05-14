package com.jiangxk.base.http

/**
 * @description com.jiangxk.base.http
 * @author jiangxk
 * @time 2020-05-13  22:38
 */
interface EntityCallback<T> {
    fun onSuccess(entity: T)
    fun onFailure(message: String?)
}