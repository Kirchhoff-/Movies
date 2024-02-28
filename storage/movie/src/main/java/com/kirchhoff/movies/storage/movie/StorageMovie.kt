package com.kirchhoff.movies.storage.movie

import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.main.NetworkMovie

interface IStorageMovie {
    fun updateInfo(movie: NetworkMovie)
    fun info(movieId: Int): NetworkMovie?
    fun updateCredits(movieId: Int, credits: NetworkEntertainmentCredits)
    fun credits(movieId: Int): NetworkEntertainmentCredits?
}

internal class StorageMovie : IStorageMovie {
    private val moviesCache: MutableMap<Int, NetworkMovie> = HashMap()
    private val creditsCache: MutableMap<Int, NetworkEntertainmentCredits> = HashMap()

    override fun updateInfo(movie: NetworkMovie) {
        moviesCache[movie.id] = movie
    }

    override fun info(movieId: Int): NetworkMovie? = moviesCache[movieId]

    override fun updateCredits(movieId: Int, credits: NetworkEntertainmentCredits) {
        creditsCache[movieId] = credits
    }

    override fun credits(movieId: Int): NetworkEntertainmentCredits? = creditsCache[movieId]
}
