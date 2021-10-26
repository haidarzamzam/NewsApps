package com.haidev.newsapps.data.source.repository

import com.haidev.newsapps.BuildConfig
import com.haidev.newsapps.data.model.NewsSourcesModel
import com.haidev.newsapps.data.source.endpoint.ApiService

class NewsSourcesRepository(
    private val apiService: ApiService
) {
    suspend fun getNewsSources(
        category: String
    ): NewsSourcesModel.Response {
        return apiService.getNewsSources(category, "en", BuildConfig.API_KEY).await()
    }

}