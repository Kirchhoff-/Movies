package com.kirchhoff.movies.repository.tv

import com.kirchhoff.movies.data.ui.core.PaginatedData
import com.kirchhoff.movies.data.ui.details.review.UIReviewsListResponse
import com.kirchhoff.movies.data.ui.details.tv.UITvCredits
import com.kirchhoff.movies.data.ui.details.tv.UITvDetails
import com.kirchhoff.movies.data.ui.main.UITv
import com.kirchhoff.movies.mapper.discover.IDiscoverMapper
import com.kirchhoff.movies.mapper.mapper.IReviewListMapper
import com.kirchhoff.movies.mapper.tv.ITvDetailsMapper
import com.kirchhoff.movies.network.services.TvService
import com.kirchhoff.movies.repository.BaseRepository
import com.kirchhoff.movies.repository.Result

class TvRepository(
    private val tvService: TvService,
    private val tvDetailsMapper: ITvDetailsMapper,
    private val reviewListMapper: IReviewListMapper,
    private val discoverMapper: IDiscoverMapper
) : BaseRepository(), ITvRepository {

    override suspend fun fetchDetails(tvId: Int): Result<UITvDetails> =
        tvDetailsMapper.createUITvDetails(apiCall {
            tvService.fetchDetails(tvId)
        })

    override suspend fun fetchSimilarTvs(tvId: Int, page: Int): Result<PaginatedData<UITv>> =
        discoverMapper.createUIDiscoverTvList(apiCall {
            tvService.fetchSimilarTv(tvId, page)
        })

    override suspend fun fetchReviewsList(tvId: Int, page: Int): Result<UIReviewsListResponse> =
        reviewListMapper.createUIReviewList(apiCall {
            tvService.fetchReviews(tvId, page)
        })

    override suspend fun fetchTvCredits(tvId: Int): Result<UITvCredits> =
        tvDetailsMapper.createUITvCredits(apiCall {
            tvService.fetchTvCredits(tvId)
        })
}
