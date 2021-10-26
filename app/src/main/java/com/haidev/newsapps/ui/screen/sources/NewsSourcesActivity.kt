package com.haidev.newsapps.ui.screen.sources

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.haidev.newsapps.R
import com.haidev.newsapps.databinding.ActivityNewsSourcesBinding
import com.haidev.newsapps.ui.base.BaseActivity
import com.haidev.newsapps.ui.screen.sources.adapter.SectionsPagerAdapter
import com.haidev.newsapps.util.Util
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
        setupTabView()
    }

    override fun setLayout() = R.layout.activity_news_sources

    override fun getViewModels() = newsSourcesViewModel

    private fun setupTabView() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding?.viewPager?.adapter = sectionsPagerAdapter
        binding?.tabLayout?.setupWithViewPager(binding?.viewPager)
        for (i in 0 until binding?.tabLayout?.tabCount!!) {
            val tab: View = (binding?.tabLayout?.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(0, 0, Util.dpToPx(16), 0)
            tab.requestLayout()
        }
    }
}