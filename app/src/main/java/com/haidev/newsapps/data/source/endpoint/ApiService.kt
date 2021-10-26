package com.haidev.newsapps.data.source.endpoint

import com.haidev.newsapps.data.model.NewsSourcesModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines/sources")
    fun getNewsSources(
        @Query("category") category: String?,
        @Query("language") language: String?,
        @Query("apiKey") api_key: String?
    ): Deferred<List<NewsSourcesModel.Response>>
}