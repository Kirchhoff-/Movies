package com.kirchhoff.movies.mapper.discover

import com.kirchhoff.movies.data.network.core.NetworkPaginated
import com.kirchhoff.movies.data.network.main.NetworkMovie
import com.kirchhoff.movies.data.network.main.NetworkTv
import com.kirchhoff.movies.data.ui.core.UIPaginated
import com.kirchhoff.movies.data.ui.main.UIMovie
import com.kirchhoff.movies.data.ui.main.UITv
import com.kirchhoff.movies.repository.Result

interface IDiscoverMapper {
    fun createUIDiscoverMovieList(discoverMovies: Result<NetworkPaginated<NetworkMovie>>): Result<UIPaginated<UIMovie>>
    fun createUIDiscoverTvList(discoverTvs: Result<NetworkPaginated<NetworkTv>>): Result<UIPaginated<UITv>>
}
