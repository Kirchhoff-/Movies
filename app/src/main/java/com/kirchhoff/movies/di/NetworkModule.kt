package com.kirchhoff.movies.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kirchhoff.movies.BuildConfig
import com.kirchhoff.movies.network.interceptors.ApiKeyInterceptor
import com.kirchhoff.movies.network.services.DiscoverService
import com.kirchhoff.movies.network.services.MovieService
import com.kirchhoff.movies.network.services.PersonService
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
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    single { get<Retrofit>().create(DiscoverService::class.java) }

    single { get<Retrofit>().create(PersonService::class.java) }

    single { get<Retrofit>().create(MovieService::class.java) }
}
