package com.haidev.newsapps.ui.screen.search

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.haidev.newsapps.R
import com.haidev.newsapps.data.model.NewsArticleModel
import com.haidev.newsapps.data.model.NewsSourcesModel
import com.haidev.newsapps.databinding.ActivitySearchNewsBinding
import com.haidev.newsapps.ui.base.BaseActivity
import com.haidev.newsapps.ui.screen.search.adapter.SearchNewsPagerAdapter
import com.haidev.newsapps.util.Util
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchNewsActivity :
    BaseActivity<ActivitySearchNewsBinding, SearchNewsViewModel>(),
    SearchNewsNavigator {

    private val searchNewsViewModel: SearchNewsViewModel by viewModel()
    private var _binding: ActivitySearchNewsBinding? = null
    private val binding get() = _binding

    var querySearch: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewDataBinding()
        searchNewsViewModel.navigator = this
        binding?.lifecycleOwner = this
        initView()
    }

    private fun initView() {
        querySearch = intent?.getStringExtra(EXTRA_QUERY) as String
        setSupportActionBar(binding?.toolbar)
        supportActionBar?.title = "Search News : $querySearch"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val sectionsPagerAdapter = SearchNewsPagerAdapter(this, supportFragmentManager)
        binding?.viewPager?.adapter = sectionsPagerAdapter
        binding?.tabLayout?.setupWithViewPager(binding?.viewPager)
        binding?.tabLayout?.setupWithViewPager(binding?.viewPager)
        for (i in 0 until binding?.tabLayout?.tabCount!!) {
            val tab: View = (binding?.tabLayout?.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(Util.dpToPx(16), 0, Util.dpToPx(16), 0)
            tab.requestLayout()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun setLayout() = R.layout.activity_search_news

    override fun getViewModels() = searchNewsViewModel

    companion object {
        const val EXTRA_QUERY = "ExtraQuery"
    }

    override fun navigateToDetailSources(data: NewsSourcesModel.Response.Source) {

    }

    override fun navigateToDetailArticle(data: NewsArticleModel.Response.Article) {

    }
}