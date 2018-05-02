package me.crazystone.study.wanandroid.entity

/**
 * Created by crazy_stone on 18-4-28.
 */
data class ArticleTotalEntity(var curPage: Int,var offset: Int,var over: Boolean,
                              var datas: List<ArticleDetailEntity>,
                              var pageCount: Int,var size: Int,var total: Int)