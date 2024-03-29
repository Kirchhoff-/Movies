package com.kirchhoff.movies.screen.tvshow.repository

import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.tvshow.data.UITvShowInfo

internal interface ITvShowRepository {
    suspend fun fetchDiscoverList(page: Int): Result<UIPaginated<UITv>>
    suspend fun fetchSimilarTvShows(tvId: Int, page: Int): Result<UIPaginated<UITv>>
    suspend fun fetchDetails(tvId: Int): Result<UITvShowInfo>
    suspend fun fetchCredits(tvId: Int): Result<UIEntertainmentCredits>
}
