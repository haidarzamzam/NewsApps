package com.haidev.newsapps.data.source.repository

import com.haidev.newsapps.BuildConfig
import com.haidev.newsapps.data.model.NewsArticleModel
import com.haidev.newsapps.data.source.endpoint.ApiService

class NewsArticleRepository(
    private val apiService: ApiService
) {
    suspend fun getNewsArticle(
        sources: String
    ): NewsArticleModel.Response {
        return apiService.getNewsArticle(sources, "en", BuildConfig.API_KEY).await()
    }

}