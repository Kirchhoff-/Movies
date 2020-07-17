package com.kirchhoff.movies.repository.tv

import com.kirchhoff.movies.data.responses.TvDetails
import com.kirchhoff.movies.network.services.TvService
import com.kirchhoff.movies.repository.BaseRepository
import com.kirchhoff.movies.repository.Result

class TvRepository(private val tvService: TvService): BaseRepository(), ITvRepository {
    override suspend fun fetchDetails(tvId: Int): Result<TvDetails> {
        return apiCall { tvService.fetchDetails(tvId) }
    }
}