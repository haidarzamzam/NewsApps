package com.haidev.newsapps.ui.screen.article

import com.haidev.newsapps.data.model.NewsArticleModel

interface NewsArticleNavigator {
    fun navigateToDetailArticle(data: NewsArticleModel.Response.Article)
}