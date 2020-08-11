package com.kirchhoff.movies.mapper.discover

import com.kirchhoff.movies.data.network.main.NetworkDiscoverMovies
import com.kirchhoff.movies.data.ui.main.UIDiscoverMovies
import com.kirchhoff.movies.mapper.BaseMapper
import com.kirchhoff.movies.repository.Result

class DiscoverMapper : BaseMapper(), IDiscoverMapper {

    override fun createUIDiscoverMovieList(discoverMovies: Result<NetworkDiscoverMovies>): Result<UIDiscoverMovies> =
        when (discoverMovies) {
            is Result.Success -> Result.Success(createUIDiscoverMovies(discoverMovies.data))
            else -> mapErrorOrException(discoverMovies)
        }

    private fun createUIDiscoverMovies(response: NetworkDiscoverMovies) =
        UIDiscoverMovies(
            response.page,
            response.results,
            response.total_results,
            response.total_pages
        )
}
