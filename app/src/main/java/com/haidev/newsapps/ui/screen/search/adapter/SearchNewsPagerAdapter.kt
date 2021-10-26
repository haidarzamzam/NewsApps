package com.haidev.newsapps.ui.screen.search.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.haidev.newsapps.R
import com.haidev.newsapps.ui.screen.search.tab.SearchNewsArticleFragment
import com.haidev.newsapps.ui.screen.search.tab.SearchNewsSourcesFragment

class SearchNewsPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> SearchNewsSourcesFragment()
            1 -> SearchNewsArticleFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence =
        mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = TAB_TITLES.size

    companion object {
        @StringRes
        private val TAB_TITLES =
            intArrayOf(
                R.string.text_search_sources,
                R.string.text_search_articles
            )
    }
}