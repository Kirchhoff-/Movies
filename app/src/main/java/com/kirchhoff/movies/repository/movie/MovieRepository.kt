package com.kirchhoff.movies.repository.movie

import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.data.ui.core.UIEntertainmentCredits
import com.kirchhoff.movies.data.ui.details.movie.UIMovieDetails
import com.kirchhoff.movies.data.ui.details.movie.UITrailersList
import com.kirchhoff.movies.mapper.discover.IDiscoverMapper
import com.kirchhoff.movies.mapper.movie.IMovieDetailsMapper
import com.kirchhoff.movies.network.services.MovieService

class MovieRepository(
    private val movieService: MovieService,
    private val movieDetailsMapper: IMovieDetailsMapper,
    private val discoverMapper: IDiscoverMapper
) : BaseRepository(), IMovieRepository {

    override suspend fun fetchDetails(movieId: Int): Result<UIMovieDetails> =
        movieDetailsMapper.createUIMovieDetails(apiCall {
            movieService.fetchDetails(movieId)
        })

    override suspend fun fetchSimilarMoviesList(
        movieId: Int,
        page: Int
    ): Result<UIPaginated<UIMovie>> =
        discoverMapper.createUIDiscoverMovieList(apiCall {
            movieService.fetchSimilarMovies(movieId, page)
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
