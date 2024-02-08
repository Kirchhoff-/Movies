package com.kirchhoff.movies.storage.movie.storage

import com.kirchhoff.movies.networkdata.main.NetworkMovie
import com.kirchhoff.movies.storage.movie.IStorageMovie

internal class StorageMovie : IStorageMovie {
    private val moviesCache: MutableMap<Int, NetworkMovie> = HashMap()

    override fun updateInfo(movie: NetworkMovie) {
        moviesCache[movie.id] = movie
    }

    override fun info(movieId: Int): NetworkMovie? = moviesCache[movieId]
}
