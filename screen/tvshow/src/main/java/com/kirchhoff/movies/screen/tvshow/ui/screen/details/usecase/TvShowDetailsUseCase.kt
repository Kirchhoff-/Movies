package com.kirchhoff.movies.screen.tvshow.ui.screen.details.usecase

import com.kirchhoff.movies.core.data.TvId
import com.kirchhoff.movies.core.data.ui.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.ui.UITv
import com.kirchhoff.movies.core.mapper.ICoreMapper
import com.kirchhoff.movies.core.repository.RepositoryResult
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.mapper.ITvShowDetailsMapper
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.model.TvShowDetails
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.repository.ITvShowDetailsRepository

internal interface ITvShowDetailsUseCase {
    suspend fun fetchDetails(id: TvId): Result<TvShowDetails>
    suspend fun fetchCredits(id: TvId): Result<UIEntertainmentCredits>
    suspend fun fetchSimilar(id: TvId, page: Int): Result<UIPaginated<UITv>>
}

internal class TvShowDetailsUseCase(
    private val tvShowDetailsRepository: ITvShowDetailsRepository,
    private val tvShowDetailsMapper: ITvShowDetailsMapper,
    private val coreMapper: ICoreMapper
) : ITvShowDetailsUseCase {

    override suspend fun fetchDetails(id: TvId): Result<TvShowDetails> =
        when (val response = tvShowDetailsRepository.details(id)) {
            is RepositoryResult.Success -> Result.success(tvShowDetailsMapper.createUITvDetails(response.data))
            else -> Result.failure(Exception("Can't fetch the details"))
        }

    override suspend fun fetchCredits(id: TvId): Result<UIEntertainmentCredits> =
        when (val response = tvShowDetailsRepository.credits(id)) {
            is RepositoryResult.Success -> Result.success(coreMapper.createUIEntertainmentCredits(response.data))
            else -> Result.failure(Exception("Can't fetch the credits"))
        }

    override suspend fun fetchSimilar(id: TvId, page: Int): Result<UIPaginated<UITv>> =
        when (val response = tvShowDetailsRepository.similar(id, page)) {
            is RepositoryResult.Success -> Result.success(tvShowDetailsMapper.createTvShowList(response.data))
            else -> Result.failure(Exception("Can't fetch the similar tv shows"))
        }
}
