package com.kirchhoff.movies

import android.app.Application
import com.kirchhoff.movies.di.facadeModule
import com.kirchhoff.movies.di.mapperModule
import com.kirchhoff.movies.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MoviesApplication)
            modules(networkModule)
            modules(mapperModule)
            modules(facadeModule)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
