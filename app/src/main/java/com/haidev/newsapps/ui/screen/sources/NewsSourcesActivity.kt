package com.haidev.newsapps.ui.screen.sources

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import com.haidev.newsapps.R
import com.haidev.newsapps.data.model.NewsSourcesModel
import com.haidev.newsapps.databinding.ActivityNewsSourcesBinding
import com.haidev.newsapps.ui.base.BaseActivity
import com.haidev.newsapps.ui.screen.article.NewsArticleActivity
import com.haidev.newsapps.ui.screen.sources.adapter.NewsSourcesPagerAdapter
import com.haidev.newsapps.util.Util
import com.haidev.newsapps.util.hideKeyboard
import org.koin.android.ext.android.inject


class NewsSourcesActivity : BaseActivity<ActivityNewsSourcesBinding, NewsSourcesViewModel>(),
    NewsSourcesNavigator {

    private val newsSourcesViewModel: NewsSourcesViewModel by inject()
    private var _binding: ActivityNewsSourcesBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewDataBinding()
        newsSourcesViewModel.navigator = this
        binding?.lifecycleOwner = this
        initView()
    }

    private fun initView() {
        setSupportActionBar(binding?.toolbar)
        val sectionsPagerAdapter = NewsSourcesPagerAdapter(this, supportFragmentManager)
        binding?.viewPager?.adapter = sectionsPagerAdapter
        binding?.tabLayout?.setupWithViewPager(binding?.viewPager)
        for (i in 0 until binding?.tabLayout?.tabCount!!) {
            val tab: View = (binding?.tabLayout?.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(0, 0, Util.dpToPx(16), 0)
            tab.requestLayout()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val item = menu?.findItem(R.id.action_search)
        val searchView = item?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                hideKeyboard()
                query?.let { navigateToSearch(it) }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun setLayout() = R.layout.activity_news_sources

    override fun getViewModels() = newsSourcesViewModel

    override fun navigateToDetailSources(data: NewsSourcesModel.Response.Source) {

    }

    override fun navigateToSearch(query: String) {
        val intent = Intent(this, NewsArticleActivity::class.java)
        intent.putExtra(NewsArticleActivity.EXTRA_QUERY, query)
        startActivity(intent)
    }
}