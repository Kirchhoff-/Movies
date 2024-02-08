package com.kirchhoff.movies.di

import com.kirchhoff.movies.storage.movie.storageMovieModule
import com.kirchhoff.movies.storage.tvshow.storageTvShowModule

val storageModules = listOf(
    storageMovieModule,
    storageTvShowModule
)
