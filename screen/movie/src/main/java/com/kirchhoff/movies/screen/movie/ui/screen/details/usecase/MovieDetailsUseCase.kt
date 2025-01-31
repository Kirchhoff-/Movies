package com.kirchhoff.movies.screen.movie.ui.screen.details.usecase

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.ui.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.ui.UIImage
import com.kirchhoff.movies.core.data.ui.UIMovie
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.screen.movie.data.UIMovieInfo
import com.kirchhoff.movies.screen.movie.data.UITrailer
import com.kirchhoff.movies.screen.movie.mapper.IMovieDetailsMapper
import com.kirchhoff.movies.screen.movie.mapper.IMovieListMapper
import com.kirchhoff.movies.screen.movie.repository.IMovieDetailsRepository
import com.kirchhoff.movies.screen.movie.repository.IMovieRepository

internal interface IMovieDetailsUseCase {
    suspend fun fetchMovie(id: MovieId): kotlin.Result<UIMovie>
    suspend fun fetchDetails(id: MovieId): kotlin.Result<UIMovieInfo>
    suspend fun fetchTrailersList(id: MovieId): kotlin.Result<List<UITrailer>>
    suspend fun fetchMovieCredits(id: MovieId): kotlin.Result<UIEntertainmentCredits>
    suspend fun fetchSimilarMovies(id: MovieId, page: Int): kotlin.Result<UIPaginated<UIMovie>>
    suspend fun fetchImages(id: MovieId): kotlin.Result<List<UIImage>>
}

internal class MovieDetailsUseCase(
    private val movieRepository: IMovieRepository,
    private val movieDetailsRepository: IMovieDetailsRepository,
    private val movieDetailsMapper: IMovieDetailsMapper,
    private val movieListMapper: IMovieListMapper
) : IMovieDetailsUseCase {

    override suspend fun fetchMovie(id: MovieId): kotlin.Result<UIMovie> =
        when (val response = movieDetailsRepository.fetchInfo(id)) {
            is Result.Success -> kotlin.Result.success(movieDetailsMapper.createUIMovie(response.data))
            else -> kotlin.Result.failure(Exception("Can't fetch the movie info"))
        }

    override suspend fun fetchDetails(id: MovieId): kotlin.Result<UIMovieInfo> =
        when (val response = movieDetailsRepository.fetchDetails(id)) {
            is Result.Success -> kotlin.Result.success(movieDetailsMapper.createUIMovieDetails(response.data))
            else -> kotlin.Result.failure(Exception("Can't fetch the details"))
        }

    override suspend fun fetchTrailersList(id: MovieId): kotlin.Result<List<UITrailer>> =
        when (val response = movieDetailsRepository.fetchTrailersList(id)) {
            is Result.Success -> kotlin.Result.success(movieDetailsMapper.createUITrailersList(response.data))
            else -> kotlin.Result.failure(Exception("Can't fetch the trailers list"))
        }

    override suspend fun fetchMovieCredits(id: MovieId): kotlin.Result<UIEntertainmentCredits> =
        when (val response = movieDetailsRepository.fetchMovieCredits(id)) {
            is Result.Success -> {
                kotlin.Result.success(
                    movieDetailsMapper.createUIEntertainmentCredits(
                        NetworkEntertainmentCredits(
                            cast = response.data.cast?.sortedByDescending { it.popularity },
                            crew = response.data.crew
                                ?.sortedByDescending { it.popularity }
                                ?.distinctBy { it.name }
                        )
                    )
                )
            }

            else -> kotlin.Result.failure(Exception("Can't fetch the movie credits"))
        }

    override suspend fun fetchSimilarMovies(id: MovieId, page: Int): kotlin.Result<UIPaginated<UIMovie>> =
        when (val response = movieDetailsRepository.fetchSimilarMovies(id, page)) {
            is Result.Success -> kotlin.Result.success(movieListMapper.createMovieList(response.data))
            else -> kotlin.Result.failure(Exception("Can't fetch the similar movies"))
        }

    override suspend fun fetchImages(id: MovieId): kotlin.Result<List<UIImage>> =
        when (val response = movieRepository.fetchImages(id)) {
            is Result.Success -> kotlin.Result.success(response.data)
            else -> kotlin.Result.failure(Exception("Can't fetch the images"))
        }
}
