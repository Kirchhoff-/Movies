package com.kirchhoff.movies.screen.tvshow.ui.screen.details.usecase

import com.kirchhoff.movies.core.data.TvId
import com.kirchhoff.movies.core.data.ui.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.ui.UITv
import com.kirchhoff.movies.core.mapper.CoreMapper
import com.kirchhoff.movies.core.repository.RepositoryResult
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.mapper.TvShowDetailsMapper
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.model.TvShowDetails
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.repository.TvShowDetailsRepository

internal class TvShowDetailsUseCase(
    private val tvShowDetailsRepository: TvShowDetailsRepository,
    private val tvShowDetailsMapper: TvShowDetailsMapper,
    private val coreMapper: CoreMapper
) {

    suspend fun fetchDetails(id: TvId): Result<TvShowDetails> =
        when (val response = tvShowDetailsRepository.details(id)) {
            is RepositoryResult.Success -> Result.success(tvShowDetailsMapper.createUITvDetails(response.data))
            else -> Result.failure(Exception("Can't fetch the details"))
        }

    suspend fun fetchCredits(id: TvId): Result<UIEntertainmentCredits> =
        when (val response = tvShowDetailsRepository.credits(id)) {
            is RepositoryResult.Success -> Result.success(coreMapper.createUIEntertainmentCredits(response.data))
            else -> Result.failure(Exception("Can't fetch the credits"))
        }

    suspend fun fetchSimilar(id: TvId, page: Int): Result<UIPaginated<UITv>> =
        when (val response = tvShowDetailsRepository.similar(id, page)) {
            is RepositoryResult.Success -> Result.success(tvShowDetailsMapper.createTvShowList(response.data))
            else -> Result.failure(Exception("Can't fetch the similar tv shows"))
        }
}
