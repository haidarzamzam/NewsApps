package com.haidev.newsapps.ui.screen.search

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haidev.newsapps.data.model.NewsArticleModel
import com.haidev.newsapps.data.model.NewsSourcesModel
import com.haidev.newsapps.data.model.Resource
import com.haidev.newsapps.data.source.repository.NewsArticleRepository
import com.haidev.newsapps.data.source.repository.NewsSourcesRepository
import com.haidev.newsapps.ui.base.BaseViewModel
import com.haidev.newsapps.util.ErrorUtils
import kotlinx.coroutines.launch

class SearchNewsViewModel(
    private val repositoryArticle: NewsArticleRepository,
    private val repositorySources: NewsSourcesRepository,
    application: Application

) : BaseViewModel<SearchNewsNavigator>(application) {
    private val _newsArticle = MutableLiveData<Resource<NewsArticleModel.Response>>()
    val newsArticle: MutableLiveData<Resource<NewsArticleModel.Response>>
        get() = _newsArticle

    fun getNewsArticleAsync(sources: String) {
        viewModelScope.launch {
            _newsArticle.postValue(Resource.loading(null))
            try {
                val response = repositoryArticle.getNewsArticle(sources)
                _newsArticle.postValue(Resource.success(response))
            } catch (t: Throwable) {
                _newsArticle.postValue(
                    Resource.error(
                        ErrorUtils.getErrorThrowableMsg(t),
                        null,
                        t
                    )
                )
            }
        }
    }

    private val _newsSources = MutableLiveData<Resource<NewsSourcesModel.Response>>()
    val newsSources: MutableLiveData<Resource<NewsSourcesModel.Response>>
        get() = _newsSources

    fun getNewsSourcesAsync(category: String) {
        viewModelScope.launch {
            _newsSources.postValue(Resource.loading(null))
            try {
                val response = repositorySources.getNewsSources(category)
                _newsSources.postValue(Resource.success(response))
            } catch (t: Throwable) {
                _newsSources.postValue(
                    Resource.error(
                        ErrorUtils.getErrorThrowableMsg(t),
                        null,
                        t
                    )
                )
            }
        }
    }
}