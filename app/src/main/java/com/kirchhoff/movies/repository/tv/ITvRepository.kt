package com.kirchhoff.movies.repository.tv

import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.data.ui.core.UIEntertainmentCredits
import com.kirchhoff.movies.data.ui.details.review.UIReview
import com.kirchhoff.movies.data.ui.details.tv.UITvDetails
import com.kirchhoff.movies.data.ui.main.UITv
import com.kirchhoff.movies.repository.Result

interface ITvRepository {
    suspend fun fetchDetails(tvId: Int): Result<UITvDetails>
    suspend fun fetchReviewsList(tvId: Int, page: Int): Result<UIPaginated<UIReview>>
    suspend fun fetchSimilarTvs(tvId: Int, page: Int): Result<UIPaginated<UITv>>
    suspend fun fetchTvCredits(tvId: Int): Result<UIEntertainmentCredits>
}
