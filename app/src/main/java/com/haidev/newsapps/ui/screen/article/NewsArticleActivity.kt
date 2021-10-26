package com.haidev.newsapps.ui.screen.article

import android.content.Intent
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.haidev.newsapps.R
import com.haidev.newsapps.data.model.NewsArticleModel
import com.haidev.newsapps.data.model.NewsSourcesModel
import com.haidev.newsapps.data.model.Resource
import com.haidev.newsapps.databinding.ActivityNewsArticleBinding
import com.haidev.newsapps.ui.base.BaseActivity
import com.haidev.newsapps.ui.screen.article.adapter.ItemNewsArticleAdapter
import com.haidev.newsapps.util.Status
import com.haidev.newsapps.util.invisible
import com.haidev.newsapps.util.observeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsArticleActivity :
    BaseActivity<ActivityNewsArticleBinding, NewsArticleViewModel>(),
    NewsArticleNavigator {

    private val newsArticleViewModel: NewsArticleViewModel by viewModel()
    private var _binding: ActivityNewsArticleBinding? = null
    private val binding get() = _binding
    private lateinit var newsSource: NewsSourcesModel.Response.Source
    private lateinit var newsArticleListAdapter: ItemNewsArticleAdapter
    private var skeletonNewsArticle: Skeleton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewDataBinding()
        binding?.lifecycleOwner = this
        newsArticleViewModel.navigator = this
        initView()
    }

    private fun initView() {
        newsSource =
            intent?.getParcelableExtra<NewsSourcesModel.Response.Source>(
                EXTRA_SOURCE
            ) as NewsSourcesModel.Response.Source
        setSupportActionBar(binding?.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = newsSource.name
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            binding?.toolbar?.navigationIcon?.colorFilter =
                BlendModeColorFilter(getColor(R.color.colorPrimary), BlendMode.SRC_ATOP)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun setLayout() = R.layout.activity_news_article

    override fun getViewModels() = newsArticleViewModel

    override fun onReadyAction() {
        initListAdapter()
        newsSource.id?.let { newsArticleViewModel.getNewsArticleAsync(it) }
    }

    private fun initListAdapter() {
        binding?.rvArticle?.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            newsArticleListAdapter = ItemNewsArticleAdapter {
                navigateToDetailArticle(it)
            }
            adapter = newsArticleListAdapter
        }
        skeletonNewsArticle =
            binding?.rvArticle?.applySkeleton(R.layout.item_skeleton_news_article_row, 8)
    }

    override fun onObserveAction() {
        super.onObserveAction()
        with(newsArticleViewModel) {
            observeActivity(newsArticle, ::handleNewsArticle)
        }
    }

    private fun handleNewsArticle(it: Resource<NewsArticleModel.Response>?) {
        when (it?.status) {
            Status.LOADING -> {
                skeletonNewsArticle?.showSkeleton()
            }
            Status.SUCCESS -> {
                it.data?.articles?.let { sources -> newsArticleListAdapter.setData(sources) }
                skeletonNewsArticle?.showOriginal()
                binding?.executePendingBindings()
            }
            Status.ERROR -> {
                binding?.rvArticle?.invisible()
                skeletonNewsArticle?.showOriginal()
                Toast.makeText(
                    this,
                    it.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
                skeletonNewsArticle?.showOriginal()
            }
        }
    }

    override fun navigateToDetailArticle(data: NewsArticleModel.Response.Article) {
        val intent = Intent(this, DetailNewsArticleActivity::class.java)
        intent.putExtra(DetailNewsArticleActivity.EXTRA_ARTICLE, data)
        startActivity(intent)
    }

    companion object {
        const val EXTRA_SOURCE = "ExtraSource"
    }
}