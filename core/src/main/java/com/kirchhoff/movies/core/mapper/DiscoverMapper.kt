package com.kirchhoff.movies.core.mapper

import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import com.kirchhoff.movies.networkdata.main.NetworkTv

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
            response.totalPages
        )

    private fun createUIDiscoverTvs(response: NetworkPaginated<NetworkTv>) =
        UIPaginated(
            response.page,
            response.results.map { createUITv(it) },
            response.totalPages
        )

    private fun createUIMovie(movie: NetworkMovie) =
        UIMovie(
            movie.id,
            movie.title,
            movie.posterPath,
            movie.backdropPath,
            movie.voteAverage
        )

    private fun createUITv(tv: NetworkTv) =
        UITv(
            tv.id,
            tv.name,
            tv.posterPath,
            tv.backdropPath,
            tv.voteAverage
        )
}
