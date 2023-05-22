package com.kirchhoff.movies.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.kirchhoff.movies.BuildConfig
import com.kirchhoff.movies.network.interceptors.ApiKeyInterceptor
import com.kirchhoff.movies.screen.tvshow.network.TvShowService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(StethoInterceptor())
            .addInterceptor(ApiKeyInterceptor())
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(TvShowService::class.java) }
}
