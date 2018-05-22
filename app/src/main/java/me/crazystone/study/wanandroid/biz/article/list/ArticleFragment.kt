package me.crazystone.study.wanandroid.biz.article.list

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import me.crazystone.study.wanandroid.R
import me.crazystone.study.wanandroid.api.Constants
import me.crazystone.study.wanandroid.base.BaseFragment
import me.crazystone.study.wanandroid.biz.article.detail.ArticleDetailActivity
import me.crazystone.study.wanandroid.entity.ArticleDetailEntity
import me.crazystone.study.wanandroid.utils.Collections
import me.crazystone.study.wanandroid.utils.Toasts

/**
 * Created by crazy_stone on 18-4-20.
 */
class ArticleFragment : BaseFragment(), IArticleView, SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener, View.OnClickListener,
        BaseQuickAdapter.OnItemClickListener {

    var recycler_view: RecyclerView? = null
    var presenter: ArticlePresenter? = null
    var swipe_layout: SwipeRefreshLayout? = null
    var page: Int = 0
    var contentAdapter: ArticleAdapter? = null
    var state: String = Constants.PullRefreshState.IDLE
    var float_top_back: FloatingActionButton? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val contentView = LayoutInflater.from(context).inflate(R.layout.fragment_content, null)
        recycler_view = contentView.findViewById(R.id.content_recycler_view)
        swipe_layout = contentView.findViewById(R.id.content_swipe_layout)
        float_top_back = contentView.findViewById(R.id.article_float_back_top)

        initView()

        presenter = ArticlePresenter(activity, this)

        refreshArticle()

        return contentView
    }

    fun initView() {
        float_top_back?.setOnClickListener(this)
        swipe_layout?.setOnRefreshListener(this)
        contentAdapter = ArticleAdapter(R.layout.item_content, null)
        contentAdapter?.setOnLoadMoreListener(this, recycler_view)
        contentAdapter?.setOnItemClickListener(this)
        recycler_view?.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        recycler_view?.layoutManager = LinearLayoutManager(context)
        recycler_view?.itemAnimator = DefaultItemAnimator()
        recycler_view?.adapter = contentAdapter
    }

    fun requestArticle() {
        presenter?.requestData(page)
    }

    private fun refreshArticle() {
        state = Constants.PullRefreshState.REFRESH
        page = 0
        requestArticle()
    }

    private fun loadMoreArticle() {
        state = Constants.PullRefreshState.LOAD_MORE
        ++page
        requestArticle()
    }

    override fun inflateRecyclerView(datas: List<ArticleDetailEntity>) {
        if (state.equals(Constants.PullRefreshState.IDLE)) {
            return
        } else if (state.equals(Constants.PullRefreshState.REFRESH)) {
            if (swipe_layout?.isRefreshing!!) {
                swipe_layout?.isRefreshing = false
            }
            contentAdapter?.setNewData(datas)
            Toasts.toast(context, "刷新完成")
        } else if (state.equals(Constants.PullRefreshState.LOAD_MORE)) {
            if (Collections.isEmpty(datas)) {
                contentAdapter?.loadMoreEnd()
                Toasts.toast(context, "没有更多数据")
            } else {
                contentAdapter?.addData(datas)
                contentAdapter?.loadMoreComplete()
            }
        }
    }

    override fun onRefresh() {
        refreshArticle()
    }

    override fun onLoadMoreRequested() {
        loadMoreArticle()
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.article_float_back_top) {
            recycler_view?.smoothScrollToPosition(0)
        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        val intent = Intent(context, ArticleDetailActivity::class.java)
        val entity = adapter?.getItem(position) as ArticleDetailEntity?
        val link = entity?.link
        Log.d(ArticleFragment::class.java.simpleName, link)
        intent.putExtra(Constants.URL, link)
        activity?.startActivity(intent)
    }

}