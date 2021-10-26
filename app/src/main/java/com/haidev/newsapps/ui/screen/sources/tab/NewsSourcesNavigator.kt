package com.haidev.newsapps.ui.screen.sources.tab

import com.haidev.newsapps.data.model.NewsSourcesModel

interface NewsSourcesNavigator {
    fun navigateToDetailSources(data: NewsSourcesModel.Response.Source)
}