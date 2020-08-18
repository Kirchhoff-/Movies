package com.kirchhoff.movies.mapper.discover

import com.kirchhoff.movies.data.network.main.NetworkDiscoverMovies
import com.kirchhoff.movies.data.network.main.NetworkDiscoverTvs
import com.kirchhoff.movies.data.ui.core.PaginatedData
import com.kirchhoff.movies.data.ui.main.UIMovie
import com.kirchhoff.movies.data.ui.main.UITv
import com.kirchhoff.movies.repository.Result

interface IDiscoverMapper {
    fun createUIDiscoverMovieList(discoverMovies: Result<NetworkDiscoverMovies>): Result<PaginatedData<UIMovie>>
    fun createUIDiscoverTvList(discoverTvs: Result<NetworkDiscoverTvs>): Result<PaginatedData<UITv>>
}
