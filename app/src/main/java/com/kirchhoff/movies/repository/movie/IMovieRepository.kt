package com.kirchhoff.movies.repository.movie

import com.kirchhoff.movies.data.responses.MovieDetails
import com.kirchhoff.movies.repository.Result

interface IMovieRepository {
    suspend fun fetchDetails(movieId: Int): Result<MovieDetails>
}