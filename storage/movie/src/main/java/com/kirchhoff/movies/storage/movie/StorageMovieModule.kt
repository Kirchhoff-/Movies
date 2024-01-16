package com.kirchhoff.movies.storage.movie

import com.kirchhoff.movies.storage.movie.storage.StorageMovie
import org.koin.dsl.module

val storageMovieModule = module {
    single<IStorageMovie> { StorageMovie() }
}
