package com.kirchhoff.movies.screen.person.ui.screen.list.repository

import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.RepositoryResult
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkPerson
import com.kirchhoff.movies.screen.person.ui.screen.list.network.PersonListService

internal interface IPersonListRepository {
    suspend fun popularPersons(page: Int): RepositoryResult<NetworkPaginated<NetworkPerson>>
}

internal class PersonListRepository(private val personListService: PersonListService) : BaseRepository(), IPersonListRepository {

    override suspend fun popularPersons(page: Int): RepositoryResult<NetworkPaginated<NetworkPerson>> = apiCall {
        personListService.fetchPopularPerson(page)
    }
}
