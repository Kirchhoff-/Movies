package com.kirchhoff.movies.repository.person

import com.kirchhoff.movies.data.responses.PersonsResponse
import com.kirchhoff.movies.data.ui.details.person.UIPersonCredits
import com.kirchhoff.movies.data.ui.details.person.UIPersonDetails
import com.kirchhoff.movies.mapper.person.IPersonDetailsMapper
import com.kirchhoff.movies.network.services.PersonService
import com.kirchhoff.movies.repository.BaseRepository
import com.kirchhoff.movies.repository.Result

class PersonsRepository(
    private val personService: PersonService,
    private val personDetailsMapper: IPersonDetailsMapper
) : BaseRepository(), IPersonsRepository {
    override suspend fun fetchPopularPersons(page: Int): Result<PersonsResponse> {
        return apiCall { personService.fetchPopularPerson(page) }
    }

    override suspend fun fetchPersonDetail(personId: Int): Result<UIPersonDetails> {
        return personDetailsMapper.createUIPersonDetails(apiCall {
            personService.fetchPersonDetail(
                personId
            )
        })
    }

    override suspend fun fetchPersonCredits(personId: Int): Result<UIPersonCredits> {
        return personDetailsMapper.createUIPersonCredits(apiCall {
            personService.fetchPersonCredits(
                personId
            )
        })
    }
}
