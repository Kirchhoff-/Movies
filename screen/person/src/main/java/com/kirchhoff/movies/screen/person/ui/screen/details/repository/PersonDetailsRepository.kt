package com.kirchhoff.movies.screen.person.ui.screen.details.repository

import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.RepositoryResult
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonCredits
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonDetails
import com.kirchhoff.movies.networkdata.details.person.NetworkPersonImages
import com.kirchhoff.movies.screen.person.ui.screen.details.network.PersonDetailsService

internal interface IPersonDetailsRepository {
    suspend fun details(personId: Int): RepositoryResult<NetworkPersonDetails>
    suspend fun credits(personId: Int): RepositoryResult<NetworkPersonCredits>
    suspend fun images(personId: Int): RepositoryResult<NetworkPersonImages>
}

internal class PersonDetailsRepository(
    private val personDetailsService: PersonDetailsService
) : BaseRepository(), IPersonDetailsRepository {

    override suspend fun details(personId: Int): RepositoryResult<NetworkPersonDetails> = apiCall {
        personDetailsService.fetchPersonDetail(personId)
    }

    override suspend fun credits(personId: Int): RepositoryResult<NetworkPersonCredits> = apiCall {
        personDetailsService.fetchPersonCredits(personId)
    }

    override suspend fun images(personId: Int): RepositoryResult<NetworkPersonImages> = apiCall {
        personDetailsService.fetchPersonImages(personId)
    }
}
