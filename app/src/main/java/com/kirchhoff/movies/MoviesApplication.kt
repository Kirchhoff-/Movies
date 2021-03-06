package com.kirchhoff.movies

import android.app.Application
import com.facebook.stetho.Stetho
import com.kirchhoff.movies.di.mapperModule
import com.kirchhoff.movies.di.networkModule
import com.kirchhoff.movies.di.repositoryModule
import com.kirchhoff.movies.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MoviesApplication)
            modules(networkModule)
            modules(repositoryModule)
            modules(viewModelModule)
            modules(mapperModule)
        }

        initStetho()
        initTimber()
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
