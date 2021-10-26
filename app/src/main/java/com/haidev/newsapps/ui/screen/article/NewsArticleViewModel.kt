package com.haidev.newsapps.ui.screen.article

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haidev.newsapps.data.model.NewsArticleModel
import com.haidev.newsapps.data.model.Resource
import com.haidev.newsapps.data.source.repository.NewsArticleRepository
import com.haidev.newsapps.ui.base.BaseViewModel
import com.haidev.newsapps.util.ErrorUtils
import kotlinx.coroutines.launch

class NewsArticleViewModel(
    private val repository: NewsArticleRepository,
    application: Application
) :
    BaseViewModel<NewsArticleNavigator>(application) {
    private val _newsArticle = MutableLiveData<Resource<NewsArticleModel.Response>>()
    val newsArticle: MutableLiveData<Resource<NewsArticleModel.Response>>
        get() = _newsArticle

    fun getNewsArticleAsync(sources: String, query: String) {
        viewModelScope.launch {
            _newsArticle.postValue(Resource.loading(null))
            try {
                val response = repository.getNewsArticle(sources, query)
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
}