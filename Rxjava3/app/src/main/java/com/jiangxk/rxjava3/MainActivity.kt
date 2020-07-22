package com.jiangxk.rxjava3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jiangxk.rxjava3.model.Repo
import com.jiangxk.rxjava3.network.Api
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.internal.operators.single.SingleObserveOn
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        justTest()
        intervalTest()
    }

    private fun retrofitTest() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        val api = retrofit.create(Api::class.java)


        api.getRepos("rengwuxian")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<MutableList<Repo>?> {
                override fun onSuccess(t: MutableList<Repo>?) {
                    text.text = "Result : ${t!![0].name}"
                }

                override fun onSubscribe(d: Disposable?) {
                    disposable = d
                    text.text = "正在请求..."
                }

                override fun onError(e: Throwable?) {
                    text.text = e?.message ?: e?.javaClass?.name
                }
            })
    }

    private fun justTest() {

        var single: Single<Int> = Single.just(1)
        val singleString = single.map(object : Function<Int, String> {
            override fun apply(t: Int?): String {
                return t.toString()
            }
        })


        singleString.subscribe(object : SingleObserver<String> {
            override fun onSuccess(t: String?) {
                text.text = t
            }

            override fun onSubscribe(d: Disposable?) {
            }

            override fun onError(e: Throwable?) {
            }
        })
    }

    private fun intervalTest() {
        Observable.interval(0, 1, TimeUnit.SECONDS)
            .delay(1, TimeUnit.SECONDS)
            .map { return@map it }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Long> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(t: Long?) {
                    text.text = t.toString()
                }

                override fun onError(e: Throwable?) {
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}