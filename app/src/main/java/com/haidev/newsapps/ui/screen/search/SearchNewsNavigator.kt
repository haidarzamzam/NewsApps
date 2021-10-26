package com.haidev.newsapps.ui.screen.search

import com.haidev.newsapps.data.model.NewsArticleModel
import com.haidev.newsapps.data.model.NewsSourcesModel

interface SearchNewsNavigator {
    fun navigateToDetailSources(data: NewsSourcesModel.Response.Source)

    fun navigateToDetailArticle(data: NewsArticleModel.Response.Article)
}