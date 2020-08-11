package com.kirchhoff.movies.mapper.discover

import com.kirchhoff.movies.data.network.main.NetworkDiscoverMovies
import com.kirchhoff.movies.data.ui.main.UIDiscoverMovies
import com.kirchhoff.movies.repository.Result

interface IDiscoverMapper {
    fun createUIDiscoverMovieList(discoverMovies: Result<NetworkDiscoverMovies>): Result<UIDiscoverMovies>
}
