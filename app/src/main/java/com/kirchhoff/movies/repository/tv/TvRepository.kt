package com.kirchhoff.movies.repository.tv

import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.data.ui.core.UIEntertainmentCredits
import com.kirchhoff.movies.data.ui.details.tv.UITvDetails
import com.kirchhoff.movies.mapper.tv.ITvDetailsMapper
import com.kirchhoff.movies.network.services.TvService

class TvRepository(
    private val tvService: TvService,
    private val tvDetailsMapper: ITvDetailsMapper
) : BaseRepository(), ITvRepository {

    override suspend fun fetchDetails(tvId: Int): Result<UITvDetails> =
        tvDetailsMapper.createUITvDetails(apiCall {
            tvService.fetchDetails(tvId)
        })

    override suspend fun fetchTvCredits(tvId: Int): Result<UIEntertainmentCredits> =
        tvDetailsMapper.createUIEntertainmentCredits(apiCall {
            tvService.fetchTvCredits(tvId)
        })
}
