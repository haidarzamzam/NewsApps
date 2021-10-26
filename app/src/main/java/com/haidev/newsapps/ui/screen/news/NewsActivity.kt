package com.haidev.newsapps.ui.screen.news

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.haidev.newsapps.R
import com.haidev.newsapps.databinding.ActivityNewsBinding
import com.haidev.newsapps.ui.base.BaseActivity
import com.haidev.newsapps.ui.screen.news.adapter.SectionsPagerAdapter
import com.haidev.newsapps.util.Util
import org.koin.android.ext.android.inject


class NewsActivity : BaseActivity<ActivityNewsBinding, NewsViewModel>(),
    NewsNavigator {

    private val newsViewModel: NewsViewModel by inject()
    private var _binding: ActivityNewsBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewDataBinding()
        newsViewModel.navigator = this
        binding?.lifecycleOwner = this
        setupTabView()
    }

    override fun setLayout() = R.layout.activity_news

    override fun getViewModels() = newsViewModel

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