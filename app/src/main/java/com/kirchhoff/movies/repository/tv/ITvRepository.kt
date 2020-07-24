package com.kirchhoff.movies.repository.tv

import com.kirchhoff.movies.data.responses.DiscoverTvsResponse
import com.kirchhoff.movies.data.responses.ReviewsListResponse
import com.kirchhoff.movies.data.responses.TvDetails
import com.kirchhoff.movies.repository.Result

interface ITvRepository {
    suspend fun fetchDetails(tvId: Int): Result<TvDetails>
    suspend fun fetchReviewsList(tvId: Int, page: Int): Result<ReviewsListResponse>
    suspend fun fetchSimilarTvs(tvId: Int, page: Int): Result<DiscoverTvsResponse>
}
