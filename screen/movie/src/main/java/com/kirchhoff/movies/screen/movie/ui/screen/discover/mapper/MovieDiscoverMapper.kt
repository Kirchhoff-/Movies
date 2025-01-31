package com.kirchhoff.movies.screen.movie.ui.screen.discover.mapper

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.ui.UIMovie
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkMovie

internal interface IMovieDiscoverMapper {
    fun mapMovieList(movieList: NetworkPaginated<NetworkMovie>): List<UIMovie>
}

internal class MovieDiscoverMapper : IMovieDiscoverMapper {
    override fun mapMovieList(movieList: NetworkPaginated<NetworkMovie>): List<UIMovie> = movieList.results.map { it.toUIMovie() }

    private fun NetworkMovie.toUIMovie(): UIMovie = UIMovie(
        id = MovieId(id),
        title = title,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage
    )
}
