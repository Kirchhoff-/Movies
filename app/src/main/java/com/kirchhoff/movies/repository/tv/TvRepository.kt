package com.kirchhoff.movies.repository.tv

import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.data.ui.core.UIEntertainmentCredits
import com.kirchhoff.movies.data.ui.details.review.UIReview
import com.kirchhoff.movies.data.ui.details.tv.UITvDetails
import com.kirchhoff.movies.data.ui.main.UITv
import com.kirchhoff.movies.mapper.discover.IDiscoverMapper
import com.kirchhoff.movies.mapper.review.IReviewListMapper
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

    override suspend fun fetchSimilarTvs(tvId: Int, page: Int): Result<UIPaginated<UITv>> =
        discoverMapper.createUIDiscoverTvList(apiCall {
            tvService.fetchSimilarTv(tvId, page)
        })

    override suspend fun fetchReviewsList(tvId: Int, page: Int): Result<UIPaginated<UIReview>> =
        reviewListMapper.createUIReviewList(apiCall {
            tvService.fetchReviews(tvId, page)
        })

    override suspend fun fetchTvCredits(tvId: Int): Result<UIEntertainmentCredits> =
        tvDetailsMapper.createUIEntertainmentCredits(apiCall {
            tvService.fetchTvCredits(tvId)
        })
}
