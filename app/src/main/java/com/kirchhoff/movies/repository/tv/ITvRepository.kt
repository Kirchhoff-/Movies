package com.kirchhoff.movies.repository.tv

import com.kirchhoff.movies.data.ui.core.PaginatedData
import com.kirchhoff.movies.data.ui.details.review.UIReview
import com.kirchhoff.movies.data.ui.details.tv.UITvCredits
import com.kirchhoff.movies.data.ui.details.tv.UITvDetails
import com.kirchhoff.movies.data.ui.main.UITv
import com.kirchhoff.movies.repository.Result

interface ITvRepository {
    suspend fun fetchDetails(tvId: Int): Result<UITvDetails>
    suspend fun fetchReviewsList(tvId: Int, page: Int): Result<PaginatedData<UIReview>>
    suspend fun fetchSimilarTvs(tvId: Int, page: Int): Result<PaginatedData<UITv>>
    suspend fun fetchTvCredits(tvId: Int): Result<UITvCredits>
}
