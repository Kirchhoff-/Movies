package com.kirchhoff.movies.repository.tv

import com.kirchhoff.movies.data.responses.TvDetails
import com.kirchhoff.movies.repository.Result

interface ITvRepository {
    suspend fun fetchDetails(tvId: Int): Result<TvDetails>
}