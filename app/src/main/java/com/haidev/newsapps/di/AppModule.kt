package com.haidev.newsapps.di

import com.haidev.newsapps.data.source.repository.NewsArticleRepository
import com.haidev.newsapps.data.source.repository.NewsSourcesRepository
import com.haidev.newsapps.ui.screen.article.NewsArticleViewModel
import com.haidev.newsapps.ui.screen.sources.NewsSourcesViewModel
import com.haidev.newsapps.ui.screen.splash.SplashViewModel
import com.haidev.newsapps.util.ContextProviders
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(androidApplication()) }
    viewModel { NewsSourcesViewModel(get(), androidApplication()) }
    viewModel { NewsArticleViewModel(get(), androidApplication()) }
}

val apiRepositoryModule = module {
    single { ContextProviders.getInstance() }
    single { NewsSourcesRepository(get()) }
    single { NewsArticleRepository(get()) }
}
