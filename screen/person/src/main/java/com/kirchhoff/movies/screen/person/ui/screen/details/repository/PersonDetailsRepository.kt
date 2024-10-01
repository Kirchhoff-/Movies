package com.kirchhoff.movies.screen.person.ui.screen.details.repository

import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonCredits
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonDetails
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonImages
import com.kirchhoff.movies.screen.person.ui.screen.details.network.PersonDetailsService

internal interface IPersonDetailsRepository {
    suspend fun details(personId: Int): Result<NetworkPersonDetails>
    suspend fun credits(personId: Int): Result<NetworkPersonCredits>
    suspend fun images(personId: Int): Result<NetworkPersonImages>
}

internal class PersonDetailsRepository(
    private val personDetailsService: PersonDetailsService
) : BaseRepository(), IPersonDetailsRepository {

    override suspend fun details(personId: Int): Result<NetworkPersonDetails> = apiCall {
        personDetailsService.fetchPersonDetail(personId)
    }

    override suspend fun credits(personId: Int): Result<NetworkPersonCredits> = apiCall {
        personDetailsService.fetchPersonCredits(personId)
    }

    override suspend fun images(personId: Int): Result<NetworkPersonImages> = apiCall {
        personDetailsService.fetchPersonImages(personId)
    }
}
