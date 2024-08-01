package com.kirchhoff.movies.screen.movie.mapper

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.mapper.BaseMapper
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkMovie

internal interface IMovieListMapper {
    fun createMovieList(movies: NetworkPaginated<NetworkMovie>): UIPaginated<UIMovie>
}

internal class MovieListMapper : BaseMapper(), IMovieListMapper {

    override fun createMovieList(movies: NetworkPaginated<NetworkMovie>): UIPaginated<UIMovie> = movies.toUIPaginated()

    private fun NetworkPaginated<NetworkMovie>.toUIPaginated(): UIPaginated<UIMovie> = UIPaginated(
        page = page,
        results = results.map { it.toUIMovie() },
        totalPages = totalPages
    )

    private fun NetworkMovie.toUIMovie(): UIMovie = UIMovie(
        id = MovieId(id),
        title = title,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage
    )
}
