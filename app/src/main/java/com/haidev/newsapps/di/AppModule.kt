package com.haidev.newsapps.di

import com.haidev.newsapps.data.source.repository.NewsSourcesRepository
import com.haidev.newsapps.ui.screen.news.NewsViewModel
import com.haidev.newsapps.ui.screen.sources.NewsSourcesViewModel
import com.haidev.newsapps.ui.screen.splash.SplashViewModel
import com.haidev.newsapps.util.ContextProviders
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(androidApplication()) }
    viewModel { NewsViewModel(androidApplication()) }
    viewModel { NewsSourcesViewModel(get(), androidApplication()) }
}

val apiRepositoryModule = module {
    single { ContextProviders.getInstance() }
    single { NewsSourcesRepository(get()) }
}
