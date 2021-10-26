package com.haidev.newsapps.ui.screen.article

import android.content.Intent
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.os.Build
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.haidev.newsapps.R
import com.haidev.newsapps.data.model.NewsArticleModel
import com.haidev.newsapps.databinding.ActivityDetailNewsArticleBinding
import com.haidev.newsapps.util.gone

class DetailNewsArticleActivity : AppCompatActivity() {
    private lateinit var newsArticle: NewsArticleModel.Response.Article
    private var _binding: ActivityDetailNewsArticleBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailNewsArticleBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)
        initView()
    }

    private fun initView() {
        newsArticle =
            intent?.getParcelableExtra<NewsArticleModel.Response.Article>(
                EXTRA_ARTICLE
            ) as NewsArticleModel.Response.Article
        setSupportActionBar(binding?.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = newsArticle.title
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            binding?.toolbar?.navigationIcon?.colorFilter =
                BlendModeColorFilter(getColor(R.color.colorPrimary), BlendMode.SRC_ATOP)
        }
        binding?.webview?.webViewClient = WebViewClient()
        newsArticle.url?.let { binding?.webview?.loadUrl(it) }
        binding?.fabShare?.setOnClickListener {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "This detail link for news ${newsArticle.url}")
            startActivity(Intent.createChooser(shareIntent, getString(R.string.app_name)))
        }
    }

    inner class WebViewClient : android.webkit.WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return false
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            binding?.progressBar?.gone()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_ARTICLE = "ExtraArticle"
    }
}
