package com.haidev.newsapps.ui.screen.sources.tab

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.haidev.newsapps.R
import com.haidev.newsapps.data.model.NewsSourcesModel
import com.haidev.newsapps.data.model.Resource
import com.haidev.newsapps.databinding.FragmentNewsBusinessSourcesBinding
import com.haidev.newsapps.ui.base.BaseFragment
import com.haidev.newsapps.util.Status
import com.haidev.newsapps.util.invisible
import com.haidev.newsapps.util.observeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsBusinessSourcesFragment :
    BaseFragment<FragmentNewsBusinessSourcesBinding, NewsSourcesViewModel>(),
    NewsSourcesNavigator {

    private val newsSourcesViewModel: NewsSourcesViewModel by viewModel()
    private var _binding: FragmentNewsBusinessSourcesBinding? = null
    private val binding get() = _binding
    private lateinit var newsSourcesListAdapter: ItemNewsSourcesAdapter
    private var skeletonNewsSources: Skeleton? = null

    override fun onInitialization() {
        super.onInitialization()
        _binding = getViewDataBinding()
        binding?.lifecycleOwner = this
        newsSourcesViewModel.navigator = this
        initItemNewsSourcesAdapter()
    }

    override fun setLayout() = R.layout.fragment_news_business_sources

    override fun getViewModels() = newsSourcesViewModel

    private fun initItemNewsSourcesAdapter() {
        binding?.rvNewsSources?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            newsSourcesListAdapter = ItemNewsSourcesAdapter {
                navigateToDetailSources(it)
            }
            adapter = newsSourcesListAdapter
        }
        skeletonNewsSources =
            binding?.rvNewsSources?.applySkeleton(R.layout.item_skeleton_news_sources_row, 8)
    }

    override fun onReadyAction() {
        initItemNewsSourcesAdapter()
        newsSourcesViewModel.getNewsSourcesAsync("business")
    }

    override fun onObserveAction() {
        super.onObserveAction()
        with(newsSourcesViewModel) {
            observeFragment(newsSources, ::handleNewsSources)
        }
    }

    private fun handleNewsSources(it: Resource<NewsSourcesModel.Response>?) {
        when (it?.status) {

            Status.LOADING -> {
                skeletonNewsSources?.showSkeleton()
            }
            Status.SUCCESS -> {
                it.data?.sources?.let { sources -> newsSourcesListAdapter.setData(sources) }
                skeletonNewsSources?.showOriginal()
                binding?.executePendingBindings()
            }
            Status.ERROR -> {
                binding?.rvNewsSources?.invisible()
                skeletonNewsSources?.showOriginal()
                Toast.makeText(
                    context,
                    it.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
                skeletonNewsSources?.showOriginal()
            }
        }
    }

    override fun navigateToDetailSources(data: NewsSourcesModel.Response.Source) {

    }
}