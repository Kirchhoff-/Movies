package com.kirchhoff.movies.screen.tvshow.ui.screen.details.usecase

import com.kirchhoff.movies.core.data.TvId
import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.mapper.ITvShowDetailsMapper
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.model.TvShowDetailsInfo
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.repository.ITvShowDetailsRepository

internal interface ITvShowDetailsUseCase {
    suspend fun fetchDetails(id: TvId): Result<TvShowDetailsInfo>
    suspend fun fetchCredits(id: TvId): Result<UIEntertainmentCredits>
    suspend fun fetchSimilar(id: TvId, page: Int): Result<UIPaginated<UITv>>
}

internal class TvShowDetailsUseCase(
    private val tvShowDetailsRepository: ITvShowDetailsRepository,
    private val tvShowDetailsMapper: ITvShowDetailsMapper
) : ITvShowDetailsUseCase {

    override suspend fun fetchDetails(id: TvId): Result<TvShowDetailsInfo> =
        tvShowDetailsMapper.createUITvDetails(tvShowDetailsRepository.fetchDetails(id))

    override suspend fun fetchCredits(id: TvId): Result<UIEntertainmentCredits> =
        tvShowDetailsMapper.createUIEntertainmentCredits(tvShowDetailsRepository.fetchCredits(id))

    override suspend fun fetchSimilar(id: TvId, page: Int): Result<UIPaginated<UITv>> =
        tvShowDetailsMapper.createTvShowList(tvShowDetailsRepository.fetchSimilar(id, page))
}
