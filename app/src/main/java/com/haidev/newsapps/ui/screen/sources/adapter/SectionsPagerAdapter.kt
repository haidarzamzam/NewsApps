package com.haidev.newsapps.ui.screen.sources.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.haidev.newsapps.R
import com.haidev.newsapps.ui.screen.sources.tab.*

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> NewsGeneralSourcesFragment()
            1 -> NewsBusinessSourcesFragment()
            2 -> NewsEntertainmentSourcesFragment()
            3 -> NewsHealthSourcesFragment()
            4 -> NewsTechnologySourcesFragment()
            5 -> NewsScienceSourcesFragment()
            6 -> NewsSportsSourcesFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence =
        mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = TAB_TITLES.size

    companion object {
        @StringRes
        private val TAB_TITLES =
            intArrayOf(
                R.string.text_sources_general,
                R.string.text_sources_business,
                R.string.text_sources_entertainment,
                R.string.text_sources_health,
                R.string.text_sources_technology,
                R.string.text_sources_science,
                R.string.text_sources_sports
            )
    }
}