package com.haidev.newsapps.di

import com.haidev.newsapps.BuildConfig
import com.haidev.newsapps.data.source.remote.provideApi
import com.haidev.newsapps.data.source.remote.provideCacheInterceptor
import com.haidev.newsapps.data.source.remote.provideHttpLoggingInterceptor
import com.haidev.newsapps.data.source.remote.provideMoshiConverter
import org.koin.dsl.module

val remoteModule = module {
    single { provideCacheInterceptor() }
    single { provideHttpLoggingInterceptor() }
    single { provideMoshiConverter() }
    single {
        provideApi(
            BuildConfig.API_URL,
            get()
        )
    }
}
