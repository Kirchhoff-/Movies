package com.kirchhoff.movies.repository.person

import com.kirchhoff.movies.data.responses.PersonsResponse
import com.kirchhoff.movies.network.services.PersonService
import com.kirchhoff.movies.repository.BaseRepository
import com.kirchhoff.movies.repository.Result

class PersonsRepository(private val personService: PersonService) : BaseRepository(), IPersonsRepository {
    override suspend fun fetchPopularPersons(page: Int): Result<PersonsResponse> {
        return apiCall { personService.fetchPopularPerson(page) }
    }
}
