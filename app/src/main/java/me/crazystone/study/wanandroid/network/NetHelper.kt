package me.crazystone.study.wanandroid.network

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.crazystone.study.wanandroid.api.API
import me.crazystone.study.wanandroid.api.Constants
import me.crazystone.study.wanandroid.entity.base.BaseEntity
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by crazy_stone on 18-4-18.
 */
object NetHelper {

    const val READ_TIME_OUT: Long = 5
    const val WRITE_TIME_OUT: Long = 5

    fun get(): API {
        var retrofit: Retrofit? = null
        retrofit = Retrofit.Builder().baseUrl(Constants.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient())
                .build()
        val api = retrofit.create(API::class.java)
        return api
    }

    fun <T : BaseEntity<T>> request(observable: Observable<T>, callback: NetCallback<T>) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.errorCode == 0) {
                        callback.onSuccess(response)
                    } else {
                        callback.onFail(response.errorCode, response.errorMsg)
                    }
                }, { throwable ->
                    callback.onError(throwable.message)
                })
    }

    fun <T: BaseEntity<T>> requests(observable: Observable<T>,callback: SimpleNetCallback<T>){
        request(observable, callback)
    }

    fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .build()
    }

    public class SimpleNetCallback<T> : NetCallback<T> {
        override fun onSuccess(t: T) {

        }

        override fun onFail(errorCode: Int, fail: String?) {
        }

        override fun onError(errorMessage: String?) {

        }
    }


}