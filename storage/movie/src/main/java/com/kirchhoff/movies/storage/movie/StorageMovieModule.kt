package com.kirchhoff.movies.storage.movie

import org.koin.dsl.module

val storageMovieModule = module {
    single<IStorageMovie> { StorageMovie() }
}
