package com.kirchhoff.movies.screen.movie.mapper

import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.mapper.BaseMapper
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkMovie

internal interface IMovieListMapper {
    fun createMovieList(movies: Result<NetworkPaginated<NetworkMovie>>): Result<UIPaginated<UIMovie>>
}

internal class MovieListMapper : BaseMapper(), IMovieListMapper {

    override fun createMovieList(movies: Result<NetworkPaginated<NetworkMovie>>): Result<UIPaginated<UIMovie>> = when (movies) {
        is Result.Success -> Result.Success(movies.data.toUIPaginated())
        else -> mapErrorOrException(movies)
    }

    private fun NetworkPaginated<NetworkMovie>.toUIPaginated(): UIPaginated<UIMovie> = UIPaginated(
        page = page,
        results = results.map { it.toUIMovie() },
        totalPages = totalPages
    )

    private fun NetworkMovie.toUIMovie(): UIMovie = UIMovie(
        id = id,
        title = title,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage
    )
}
