package com.kirchhoff.movies.storage.movie

import com.kirchhoff.movies.networkdata.main.NetworkMovie

interface IStorageMovie {
    fun updateInfo(movie: NetworkMovie)
    fun info(movieId: Int): NetworkMovie?
}

internal class StorageMovie : IStorageMovie {
    private val moviesCache: MutableMap<Int, NetworkMovie> = HashMap()

    override fun updateInfo(movie: NetworkMovie) {
        moviesCache[movie.id] = movie
    }

    override fun info(movieId: Int): NetworkMovie? = moviesCache[movieId]
}
