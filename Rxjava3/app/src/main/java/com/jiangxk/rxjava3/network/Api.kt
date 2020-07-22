package com.jiangxk.rxjava3.network

import com.jiangxk.rxjava3.model.Repo
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @description com.jiangxk.rxjava3.network
 * @author jiangxk
 * @time 2020/7/20  11:53 AM
 */
interface Api {

    @GET("users/{username}/repos")
    fun getRepos(@Path("username") username: String): Single<MutableList<Repo>>
}