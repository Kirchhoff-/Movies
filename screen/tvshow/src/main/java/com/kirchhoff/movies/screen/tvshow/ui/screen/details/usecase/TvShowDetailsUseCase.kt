package com.kirchhoff.movies.screen.tvshow.ui.screen.details.usecase

import com.kirchhoff.movies.core.data.TvId
import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.mapper.ICoreMapper
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
    private val tvShowDetailsMapper: ITvShowDetailsMapper,
    private val coreMapper: ICoreMapper
) : ITvShowDetailsUseCase {

    override suspend fun fetchDetails(id: TvId): Result<TvShowDetailsInfo> =
        when (val response = tvShowDetailsRepository.fetchDetails(id)) {
            is Result.Success -> Result.Success(tvShowDetailsMapper.createUITvDetails(response.data))
            else -> response.mapErrorOrException()
        }

    override suspend fun fetchCredits(id: TvId): Result<UIEntertainmentCredits> =
        when (val response = tvShowDetailsRepository.fetchCredits(id)) {
            is Result.Success -> Result.Success(coreMapper.createUIEntertainmentCredits(response.data))
            else -> response.mapErrorOrException()
        }

    override suspend fun fetchSimilar(id: TvId, page: Int): Result<UIPaginated<UITv>> =
        when (val response = tvShowDetailsRepository.fetchSimilar(id, page)) {
            is Result.Success -> Result.Success(tvShowDetailsMapper.createTvShowList(response.data))
            else -> response.mapErrorOrException()
        }
}
