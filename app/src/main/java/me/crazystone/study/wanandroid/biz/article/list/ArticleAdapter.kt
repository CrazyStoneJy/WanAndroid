package me.crazystone.study.wanandroid.biz.article.list

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import me.crazystone.study.wanandroid.R
import me.crazystone.study.wanandroid.entity.ArticleDetailEntity

/**
 * Created by crazy_stone on 18-4-20.
 */
class ArticleAdapter(var layoutId: Int, var datas: List<ArticleDetailEntity>?) : BaseQuickAdapter<ArticleDetailEntity, BaseViewHolder>(layoutId, datas) {

    override fun convert(helper: BaseViewHolder?, item: ArticleDetailEntity?) {
        helper?.setText(R.id.content_txt_desc, item?.chapterName)
        helper?.setText(R.id.content_txt_author, item?.author)
        helper?.setText(R.id.content_txt_date, item?.niceDate)
    }
}