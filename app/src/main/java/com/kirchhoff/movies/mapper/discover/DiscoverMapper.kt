package com.kirchhoff.movies.mapper.discover

import com.kirchhoff.movies.data.network.core.NetworkPaginated
import com.kirchhoff.movies.data.network.main.NetworkMovie
import com.kirchhoff.movies.data.network.main.NetworkTv
import com.kirchhoff.movies.data.ui.core.UIPaginated
import com.kirchhoff.movies.data.ui.main.UIMovie
import com.kirchhoff.movies.data.ui.main.UITv
import com.kirchhoff.movies.mapper.BaseMapper
import com.kirchhoff.movies.repository.Result

class DiscoverMapper : BaseMapper(), IDiscoverMapper {

    override fun createUIDiscoverMovieList(discoverMovies: Result<NetworkPaginated<NetworkMovie>>): Result<UIPaginated<UIMovie>> =
        when (discoverMovies) {
            is Result.Success -> Result.Success(createUIDiscoverMovies(discoverMovies.data))
            else -> mapErrorOrException(discoverMovies)
        }

    override fun createUIDiscoverTvList(discoverTvs: Result<NetworkPaginated<NetworkTv>>): Result<UIPaginated<UITv>> =
        when (discoverTvs) {
            is Result.Success -> Result.Success(createUIDiscoverTvs(discoverTvs.data))
            else -> mapErrorOrException(discoverTvs)
        }

    private fun createUIDiscoverMovies(response: NetworkPaginated<NetworkMovie>) =
        UIPaginated(
            response.page,
            response.results.map { createUIMovie(it) },
            response.total_pages
        )

    private fun createUIDiscoverTvs(response: NetworkPaginated<NetworkTv>) =
        UIPaginated(
            response.page,
            response.results.map { createUITv(it) },
            response.total_pages
        )

    private fun createUIMovie(movie: NetworkMovie) =
        UIMovie(
            movie.id,
            movie.title,
            movie.poster_path,
            movie.backdrop_path
        )

    private fun createUITv(tv: NetworkTv) =
        UITv(
            tv.id,
            tv.poster_path,
            tv.backdrop_path,
            tv.name
        )
}
