package com.haidev.newsapps.ui.screen.sources

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haidev.newsapps.data.model.NewsSourcesModel
import com.haidev.newsapps.data.model.Resource
import com.haidev.newsapps.data.source.repository.NewsSourcesRepository
import com.haidev.newsapps.ui.base.BaseViewModel
import com.haidev.newsapps.util.ErrorUtils
import kotlinx.coroutines.launch

class NewsSourcesViewModel(
    private val repository: NewsSourcesRepository,
    application: Application
) :
    BaseViewModel<NewsSourcesNavigator>(application) {
    private val _newsSources = MutableLiveData<Resource<NewsSourcesModel.Response>>()
    val newsSources: MutableLiveData<Resource<NewsSourcesModel.Response>>
        get() = _newsSources

    fun getNewsSourcesAsync(category: String) {
        viewModelScope.launch {
            _newsSources.postValue(Resource.loading(null))
            try {
                val response = repository.getNewsSources(category)
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