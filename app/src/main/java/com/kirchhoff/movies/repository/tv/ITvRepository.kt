package com.kirchhoff.movies.repository.tv

import com.kirchhoff.movies.data.ui.details.review.UIReviewsListResponse
import com.kirchhoff.movies.data.ui.details.tv.UITvCredits
import com.kirchhoff.movies.data.ui.details.tv.UITvDetails
import com.kirchhoff.movies.data.ui.main.UIDiscoverTvs
import com.kirchhoff.movies.repository.Result

interface ITvRepository {
    suspend fun fetchDetails(tvId: Int): Result<UITvDetails>
    suspend fun fetchReviewsList(tvId: Int, page: Int): Result<UIReviewsListResponse>
    suspend fun fetchSimilarTvs(tvId: Int, page: Int): Result<UIDiscoverTvs>
    suspend fun fetchTvCredits(tvId: Int): Result<UITvCredits>
}
