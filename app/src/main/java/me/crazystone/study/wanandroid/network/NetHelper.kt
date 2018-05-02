package me.crazystone.study.wanandroid.network

import me.crazystone.study.wanandroid.api.API
import me.crazystone.study.wanandroid.api.Constants
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


    fun init(): API {
        var retrofit: Retrofit? = null
        retrofit = Retrofit.Builder().baseUrl(Constants.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient())
                .build()
        val api = retrofit.create(API::class.java)
        return api
    }


    fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .build()
    }


}