package com.kirchhoff.movies.screen.movie.ui.screen.list.usecase

import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.movie.R
import com.kirchhoff.movies.screen.movie.repository.IMovieRepository
import com.kirchhoff.movies.screen.movie.ui.screen.list.MovieListType

internal interface IMovieListTitleUseCase {
    suspend fun title(movieListType: MovieListType): StringValue
}

internal class MovieListTitleUseCase(private val movieRepository: IMovieRepository) : IMovieListTitleUseCase {

    override suspend fun title(movieListType: MovieListType): StringValue = when (movieListType) {
        is MovieListType.Genre -> StringValue.IdText(R.string.movie_movies_with_genre_format, movieListType.genre.name)
        is MovieListType.Country -> StringValue.IdText(R.string.movie_movies_from_country_format, movieListType.country.name)
        is MovieListType.Similar -> when (val movieResult = movieRepository.fetchMovie(movieListType.movieId)) {
            is Result.Success -> StringValue.IdText(
                R.string.movie_similar_to_format,
                movieResult.data.title
            )
            else -> throw IllegalArgumentException("Can't create title for $movieListType")
        }
        is MovieListType.Company -> StringValue.IdText(R.string.movie_movies_by_company_format, movieListType.company.name)
        is MovieListType.NowPlaying -> StringValue.IdText(R.string.movie_now_playing)
        is MovieListType.Upcoming -> StringValue.IdText(R.string.movie_upcoming)
        is MovieListType.Popular -> StringValue.IdText(R.string.movie_popular)
        is MovieListType.TopRated -> StringValue.IdText(R.string.movie_top_rated)
    }
}
