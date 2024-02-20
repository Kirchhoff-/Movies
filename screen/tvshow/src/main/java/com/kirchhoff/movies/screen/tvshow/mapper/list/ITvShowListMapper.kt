package com.kirchhoff.movies.screen.tvshow.mapper.list

import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkTv

internal interface ITvShowListMapper {
    fun createTvShowList(tvShowResult: Result<NetworkPaginated<NetworkTv>>): Result<UIPaginated<UITv>>
}
