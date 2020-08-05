package com.kirchhoff.movies.repository.tv

import com.kirchhoff.movies.data.responses.DiscoverTvsResponse
import com.kirchhoff.movies.data.responses.ReviewsListResponse
import com.kirchhoff.movies.data.ui.details.tv.UITvCredits
import com.kirchhoff.movies.data.ui.details.tv.UITvDetails
import com.kirchhoff.movies.repository.Result

interface ITvRepository {
    suspend fun fetchDetails(tvId: Int): Result<UITvDetails>
    suspend fun fetchReviewsList(tvId: Int, page: Int): Result<ReviewsListResponse>
    suspend fun fetchSimilarTvs(tvId: Int, page: Int): Result<DiscoverTvsResponse>
    suspend fun fetchTvCredits(tvId: Int): Result<UITvCredits>
}
