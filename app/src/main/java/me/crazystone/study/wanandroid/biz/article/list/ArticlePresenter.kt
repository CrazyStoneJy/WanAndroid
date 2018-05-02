package me.crazystone.study.wanandroid.biz.article.list

import android.content.Context
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.crazystone.study.wanandroid.base.BasePresenter
import me.crazystone.study.wanandroid.network.NetHelper

/**
 * Created by crazy_stone on 18-4-20.
 */
class ArticlePresenter(var context: Context?, var ui: IArticleView) : BasePresenter(context, ui), IArticlePresenter {

    var TAG = ArticlePresenter::class.java.simpleName

    override fun requestData(page: Int) {
        NetHelper.init().getData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response != null) {
                        Log.d(TAG, response.toString())
                        val res = response.data
                        if (res != null) {
                            ui.inflateRecyclerView(res.datas)
                        }
                    }
                }, { error ->
                    Log.d(TAG, error.message)
                })
    }


}