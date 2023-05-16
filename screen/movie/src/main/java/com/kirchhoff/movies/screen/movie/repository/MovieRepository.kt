package com.kirchhoff.movies.screen.movie.repository

import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.mapper.IDiscoverMapper
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.movie.data.UIMovieDetails
import com.kirchhoff.movies.screen.movie.data.UITrailersList
import com.kirchhoff.movies.screen.movie.mapper.details.IMovieDetailsMapper
import com.kirchhoff.movies.screen.movie.network.MovieService

class MovieRepository(
    private val movieService: MovieService,
    private val movieDetailsMapper: IMovieDetailsMapper,
    private val discoverMapper: IDiscoverMapper
) : BaseRepository(), IMovieRepository {

    override suspend fun fetchDiscoverList(page: Int): Result<UIPaginated<UIMovie>> =
        discoverMapper.createUIDiscoverMovieList(apiCall {
            movieService.fetchDiscoverList(page)
        })

    override suspend fun fetchDetails(movieId: Int): Result<UIMovieDetails> =
        movieDetailsMapper.createUIMovieDetails(apiCall {
            movieService.fetchDetails(movieId)
        })

    override suspend fun fetchTrailersList(movieId: Int): Result<UITrailersList> =
        movieDetailsMapper.createUITrailersList(apiCall {
            movieService.fetchTrailersList(movieId)
        })

    override suspend fun fetchMovieCredits(movieId: Int): Result<UIEntertainmentCredits> =
        movieDetailsMapper.createUIEntertainmentCredits(apiCall {
            movieService.fetchMovieCredits(movieId)
        })
}
