package com.kirchhoff.movies.screen.person.ui.screen.list.repository

import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkPerson
import com.kirchhoff.movies.screen.person.ui.screen.list.network.PersonListService

internal interface IPersonListRepository {
    suspend fun fetchPopularPersons(page: Int): Result<NetworkPaginated<NetworkPerson>>
}

internal class PersonListRepository(private val personListService: PersonListService) : BaseRepository(), IPersonListRepository {

    override suspend fun fetchPopularPersons(page: Int): Result<NetworkPaginated<NetworkPerson>> = apiCall {
        personListService.fetchPopularPerson(page)
    }
}
