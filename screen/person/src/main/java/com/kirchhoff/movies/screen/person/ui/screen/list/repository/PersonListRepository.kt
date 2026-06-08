package com.kirchhoff.movies.screen.person.ui.screen.list.repository

import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.RepositoryResult
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkPerson
import com.kirchhoff.movies.screen.person.ui.screen.list.network.PersonListService

internal class PersonListRepository(private val personListService: PersonListService) : BaseRepository() {
    suspend fun popularPersons(page: Int): RepositoryResult<NetworkPaginated<NetworkPerson>> = apiCall {
        personListService.fetchPopularPerson(page)
    }
}
