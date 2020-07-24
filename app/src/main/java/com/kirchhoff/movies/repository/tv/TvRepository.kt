package com.kirchhoff.movies.repository.tv

import com.kirchhoff.movies.data.responses.DiscoverTvsResponse
import com.kirchhoff.movies.data.responses.ReviewsListResponse
import com.kirchhoff.movies.data.responses.TvDetails
import com.kirchhoff.movies.network.services.TvService
import com.kirchhoff.movies.repository.BaseRepository
import com.kirchhoff.movies.repository.Result

class TvRepository(private val tvService: TvService) : BaseRepository(), ITvRepository {
    override suspend fun fetchDetails(tvId: Int): Result<TvDetails> {
        return apiCall { tvService.fetchDetails(tvId) }
    }

    override suspend fun fetchSimilarTvs(tvId: Int, page: Int): Result<DiscoverTvsResponse> {
        return apiCall { tvService.fetchSimilarTv(tvId, page) }
    }

    override suspend fun fetchReviewsList(tvId: Int, page: Int): Result<ReviewsListResponse> {
        return apiCall { tvService.fetchReviews(tvId, page) }
    }
}
