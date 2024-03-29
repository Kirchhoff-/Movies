package com.kirchhoff.movies.core.mapper

import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import com.kirchhoff.movies.networkdata.main.NetworkTv

interface IDiscoverMapper {
    fun createUIDiscoverMovieList(discoverMovies: Result<NetworkPaginated<NetworkMovie>>): Result<UIPaginated<UIMovie>>
    fun createUIDiscoverTvList(discoverTvs: Result<NetworkPaginated<NetworkTv>>): Result<UIPaginated<UITv>>
}
