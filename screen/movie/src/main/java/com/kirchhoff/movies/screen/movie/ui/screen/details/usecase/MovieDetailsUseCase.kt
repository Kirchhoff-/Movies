package com.kirchhoff.movies.screen.movie.ui.screen.details.usecase

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UIImage
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.movie.data.UIMovieInfo
import com.kirchhoff.movies.screen.movie.data.UITrailersList
import com.kirchhoff.movies.screen.movie.mapper.IMovieDetailsMapper
import com.kirchhoff.movies.screen.movie.mapper.IMovieListMapper
import com.kirchhoff.movies.screen.movie.repository.IMovieDetailsRepository
import com.kirchhoff.movies.screen.movie.repository.IMovieRepository

internal interface IMovieDetailsUseCase {
    suspend fun fetchDetails(id: MovieId): Result<UIMovieInfo>
    suspend fun fetchTrailersList(id: MovieId): Result<UITrailersList>
    suspend fun fetchMovieCredits(id: MovieId): Result<UIEntertainmentCredits>
    suspend fun fetchSimilarMovies(id: MovieId, page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchImages(id: MovieId): Result<List<UIImage>>
}

internal class MovieDetailsUseCase(
    private val movieRepository: IMovieRepository,
    private val movieDetailsRepository: IMovieDetailsRepository,
    private val movieDetailsMapper: IMovieDetailsMapper,
    private val movieListMapper: IMovieListMapper
) : IMovieDetailsUseCase {

    override suspend fun fetchDetails(id: MovieId): Result<UIMovieInfo> =
        movieDetailsMapper.createUIMovieDetails(movieDetailsRepository.fetchDetails(id))

    override suspend fun fetchTrailersList(id: MovieId): Result<UITrailersList> =
        movieDetailsMapper.createUITrailersList(movieDetailsRepository.fetchTrailersList(id))

    override suspend fun fetchMovieCredits(id: MovieId): Result<UIEntertainmentCredits> =
        movieDetailsMapper.createUIEntertainmentCredits(movieDetailsRepository.fetchMovieCredits(id))

    override suspend fun fetchSimilarMovies(id: MovieId, page: Int): Result<UIPaginated<UIMovie>> =
        movieListMapper.createMovieList(movieDetailsRepository.fetchSimilarMovies(id, page))

    override suspend fun fetchImages(id: MovieId): Result<List<UIImage>> = movieRepository.fetchImages(id)
}
