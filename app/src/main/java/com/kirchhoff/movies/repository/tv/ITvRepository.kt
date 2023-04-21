package com.kirchhoff.movies.repository.tv

import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.data.ui.core.UIEntertainmentCredits
import com.kirchhoff.movies.data.ui.details.tv.UITvDetails

interface ITvRepository {
    suspend fun fetchDetails(tvId: Int): Result<UITvDetails>
    suspend fun fetchSimilarTvs(tvId: Int, page: Int): Result<UIPaginated<UITv>>
    suspend fun fetchTvCredits(tvId: Int): Result<UIEntertainmentCredits>
}
