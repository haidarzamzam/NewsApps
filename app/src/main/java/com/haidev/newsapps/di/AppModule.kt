package com.haidev.newsapps.di

import com.haidev.newsapps.ui.screen.news.NewsViewModel
import com.haidev.newsapps.ui.screen.splash.SplashViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(androidApplication()) }
    viewModel { NewsViewModel(androidApplication()) }
}

val apiRepositoryModule = module {
}
