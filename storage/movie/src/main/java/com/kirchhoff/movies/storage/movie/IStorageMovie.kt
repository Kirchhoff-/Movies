package com.kirchhoff.movies.storage.movie

import com.kirchhoff.movies.networkdata.main.NetworkMovie

interface IStorageMovie {
    fun updateInfo(movie: NetworkMovie)
    fun info(movieId: Int): NetworkMovie?
}
