package me.crazystone.study.wanandroid.biz.article.list

import me.crazystone.study.wanandroid.base.IBaseView
import me.crazystone.study.wanandroid.entity.ArticleDetailEntity
import me.crazystone.study.wanandroid.entity.BannerEntity

/**
 * Created by crazy_stone on 18-4-20.
 */
interface IArticleView : IBaseView {
    fun inflateRecyclerView(datas:List<ArticleDetailEntity>)

    fun inflateBanner(datas: List<BannerEntity>)
}