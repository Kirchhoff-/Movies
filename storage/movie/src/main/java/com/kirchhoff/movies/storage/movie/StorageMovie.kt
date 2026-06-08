package com.kirchhoff.movies.storage.movie

import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.main.NetworkMovie

class StorageMovie {
    private val moviesCache: MutableMap<Int, NetworkMovie> = HashMap()
    private val creditsCache: MutableMap<Int, NetworkEntertainmentCredits> = HashMap()

    fun updateInfo(movie: NetworkMovie) {
        moviesCache[movie.id] = movie
    }

    fun info(movieId: Int): NetworkMovie? = moviesCache[movieId]

    fun updateCredits(movieId: Int, credits: NetworkEntertainmentCredits) {
        creditsCache[movieId] = credits
    }

    fun credits(movieId: Int): NetworkEntertainmentCredits? = creditsCache[movieId]
}
