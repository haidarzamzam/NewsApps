package com.haidev.newsapps.data.source.repository

import com.haidev.newsapps.BuildConfig
import com.haidev.newsapps.data.model.NewsSourcesModel
import com.haidev.newsapps.data.source.endpoint.ApiService
import com.haidev.newsapps.util.ContextProviders

class NewsSourcesRepository(
    private val apiService: ApiService,
    private val coroutineContext: ContextProviders
) {
    suspend fun getNewsSources(
        category: String,
        language: String
    ): List<NewsSourcesModel.Response> {
        return apiService.getNewsSources(category, language, BuildConfig.API_KEY).await()
    }

}