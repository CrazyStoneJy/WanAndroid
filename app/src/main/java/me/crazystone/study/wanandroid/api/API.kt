package me.crazystone.study.wanandroid.api

import io.reactivex.Observable
import me.crazystone.study.wanandroid.entity.response.ArticleResponse
import me.crazystone.study.wanandroid.entity.response.BannerResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by crazy_stone on 18-4-18.
 */
interface API {

    @GET("article/list/{pageNum}/json")
    fun getData(@Path("pageNum") pageNum: Int): Observable<ArticleResponse>

    @GET("banner/json")
    fun getBanner(): Observable<BannerResponse>


}