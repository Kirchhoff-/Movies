package com.kirchhoff.movies.repository.movie

import com.kirchhoff.movies.data.responses.MovieDetails
import com.kirchhoff.movies.network.services.MovieService
import com.kirchhoff.movies.repository.BaseRepository
import com.kirchhoff.movies.repository.Result

class MovieRepository(private val movieService: MovieService) : BaseRepository(), IMovieRepository {
    override suspend fun fetchDetails(movieId: Int): Result<MovieDetails> {
        return apiCall { movieService.fetchDetails(movieId) }
    }
}