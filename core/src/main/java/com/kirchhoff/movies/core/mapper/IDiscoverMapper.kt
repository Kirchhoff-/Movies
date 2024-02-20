package com.kirchhoff.movies.core.mapper

import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkTv

interface IDiscoverMapper {
    fun createUIDiscoverTvList(discoverTvs: Result<NetworkPaginated<NetworkTv>>): Result<UIPaginated<UITv>>
}
