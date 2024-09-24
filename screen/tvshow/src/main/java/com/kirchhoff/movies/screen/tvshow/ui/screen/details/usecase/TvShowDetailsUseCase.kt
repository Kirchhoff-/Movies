package com.kirchhoff.movies.screen.tvshow.ui.screen.details.usecase

import com.kirchhoff.movies.core.data.TvId
import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.mapper.ICoreMapper
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.mapper.ITvShowDetailsMapper
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.model.TvShowDetails
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.repository.ITvShowDetailsRepository

internal interface ITvShowDetailsUseCase {
    suspend fun fetchDetails(id: TvId): kotlin.Result<TvShowDetails>
    suspend fun fetchCredits(id: TvId): kotlin.Result<UIEntertainmentCredits>
    suspend fun fetchSimilar(id: TvId, page: Int): kotlin.Result<UIPaginated<UITv>>
}

internal class TvShowDetailsUseCase(
    private val tvShowDetailsRepository: ITvShowDetailsRepository,
    private val tvShowDetailsMapper: ITvShowDetailsMapper,
    private val coreMapper: ICoreMapper
) : ITvShowDetailsUseCase {

    override suspend fun fetchDetails(id: TvId): kotlin.Result<TvShowDetails> =
        when (val response = tvShowDetailsRepository.fetchDetails(id)) {
            is Result.Success -> kotlin.Result.success(tvShowDetailsMapper.createUITvDetails(response.data))
            else -> kotlin.Result.failure(Exception("Can't fetch the details"))
        }

    override suspend fun fetchCredits(id: TvId): kotlin.Result<UIEntertainmentCredits> =
        when (val response = tvShowDetailsRepository.fetchCredits(id)) {
            is Result.Success -> kotlin.Result.success(coreMapper.createUIEntertainmentCredits(response.data))
            else -> kotlin.Result.failure(Exception("Can't fetch the credits"))
        }

    override suspend fun fetchSimilar(id: TvId, page: Int): kotlin.Result<UIPaginated<UITv>> =
        when (val response = tvShowDetailsRepository.fetchSimilar(id, page)) {
            is Result.Success -> kotlin.Result.success(tvShowDetailsMapper.createTvShowList(response.data))
            else -> kotlin.Result.failure(Exception("Can't fetch the similar tv shows"))
        }
}
