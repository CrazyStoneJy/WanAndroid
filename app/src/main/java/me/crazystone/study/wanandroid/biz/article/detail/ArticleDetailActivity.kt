package me.crazystone.study.wanandroid.biz.article.detail

import android.os.Bundle
import android.util.Log
import android.webkit.*
import me.crazystone.study.wanandroid.R
import me.crazystone.study.wanandroid.api.Constants
import me.crazystone.study.wanandroid.base.BaseActivity
import me.crazystone.study.wanandroid.utils.Toasts

/**
 * Created by crazy_stone on 18-5-2.
 */
class ArticleDetailActivity : BaseActivity() {

//    @BindView(R.id.article_detail_web)
    var web_article_detail: WebView? = null
    var url:String? = null

    override fun setLayoutId() {
        setContentView(R.layout.activity_article_detail)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        web_article_detail = findViewById(R.id.article_detail_web)
        initWebView()
    }

    private fun initWebView() {
        if (intent != null) {
            url = intent.getStringExtra(Constants.URL)
            Log.d(ArticleDetailActivity::class.java.simpleName,url)
            web_article_detail?.loadUrl(url)
            // could have xss attack???
            web_article_detail?.settings?.javaScriptEnabled = true
//            web_article_detail?.webViewClient = MyWebClient(url)
            web_article_detail?.webChromeClient = MyWebChromeClient()
        } else {
            Toasts.toast(this, "该链接飞到火星了")
        }
    }

    class MyWebChromeClient: WebChromeClient(){
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
//           ArticleDetailActivity@r
        }

    }

    class MyWebClient(var url:String?): WebViewClient(){
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            view?.loadUrl(url)
            return true
        }

        override fun onRenderProcessGone(view: WebView?, detail: RenderProcessGoneDetail?): Boolean {
            return super.onRenderProcessGone(view, detail)
        }
    }

}