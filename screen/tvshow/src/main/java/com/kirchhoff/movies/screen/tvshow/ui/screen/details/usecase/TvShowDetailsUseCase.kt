package com.kirchhoff.movies.screen.tvshow.ui.screen.details.usecase

import com.kirchhoff.movies.core.data.TvId
import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.tvshow.data.UITvShowInfo
import com.kirchhoff.movies.screen.tvshow.mapper.ITvShowDetailsMapper
import com.kirchhoff.movies.screen.tvshow.mapper.ITvShowListMapper
import com.kirchhoff.movies.screen.tvshow.repository.ITvShowRepository

internal interface ITvShowDetailsUseCase {
    suspend fun fetchDetails(id: TvId): Result<UITvShowInfo>
    suspend fun fetchCredits(id: TvId): Result<UIEntertainmentCredits>
    suspend fun fetchSimilar(id: TvId, page: Int): Result<UIPaginated<UITv>>
}

internal class TvShowDetailsUseCase(
    private val tvShowRepository: ITvShowRepository,
    private val tvShowListMapper: ITvShowListMapper,
    private val tvShowDetailsMapper: ITvShowDetailsMapper
) : ITvShowDetailsUseCase {

    override suspend fun fetchDetails(id: TvId): Result<UITvShowInfo> =
        tvShowDetailsMapper.createUITvDetails(tvShowRepository.fetchDetails(id))

    override suspend fun fetchCredits(id: TvId): Result<UIEntertainmentCredits> =
        tvShowDetailsMapper.createUIEntertainmentCredits(tvShowRepository.fetchCredits(id))

    override suspend fun fetchSimilar(id: TvId, page: Int): Result<UIPaginated<UITv>> =
        tvShowListMapper.createTvShowList(tvShowRepository.fetchSimilar(id, page))
}
