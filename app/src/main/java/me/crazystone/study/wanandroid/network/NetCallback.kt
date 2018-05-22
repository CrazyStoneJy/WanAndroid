package me.crazystone.study.wanandroid.network

/**
 * Created by crazy_stone on 18-5-22.
 */
interface NetCallback<T> {

    fun onSuccess(t: T)

    // 表示网络请求成功,后台返回的码不对
    fun onFail(errorCode: Int,fail: String?)

    // 表示网络连接时发生了错误
    fun onError(errorMessage: String?)


}