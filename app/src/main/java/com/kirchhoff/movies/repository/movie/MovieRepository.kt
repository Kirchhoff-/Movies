package com.kirchhoff.movies.repository.movie

import com.kirchhoff.movies.data.ui.core.UIPaginated
import com.kirchhoff.movies.data.ui.details.movie.UIMovieCredits
import com.kirchhoff.movies.data.ui.details.movie.UIMovieDetails
import com.kirchhoff.movies.data.ui.details.movie.UITrailersList
import com.kirchhoff.movies.data.ui.details.review.UIReview
import com.kirchhoff.movies.data.ui.main.UIMovie
import com.kirchhoff.movies.mapper.discover.IDiscoverMapper
import com.kirchhoff.movies.mapper.movie.IMovieDetailsMapper
import com.kirchhoff.movies.mapper.review.IReviewListMapper
import com.kirchhoff.movies.network.services.MovieService
import com.kirchhoff.movies.repository.BaseRepository
import com.kirchhoff.movies.repository.Result

class MovieRepository(
    private val movieService: MovieService,
    private val movieDetailsMapper: IMovieDetailsMapper,
    private val reviewListMapper: IReviewListMapper,
    private val discoverMapper: IDiscoverMapper
) : BaseRepository(), IMovieRepository {

    override suspend fun fetchDetails(movieId: Int): Result<UIMovieDetails> =
        movieDetailsMapper.createUIMovieDetails(apiCall {
            movieService.fetchDetails(movieId)
        })

    override suspend fun fetchReviewsList(movieId: Int, page: Int): Result<UIPaginated<UIReview>> =
        reviewListMapper.createUIReviewList(apiCall {
            movieService.fetchReviews(movieId, page)
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

    override suspend fun fetchMovieCredits(movieId: Int): Result<UIMovieCredits> =
        movieDetailsMapper.createUIMovieCredits(apiCall {
            movieService.fetchMovieCredits(movieId)
        })
}
