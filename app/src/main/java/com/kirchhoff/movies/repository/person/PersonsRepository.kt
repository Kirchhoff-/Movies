package com.kirchhoff.movies.repository.person

import com.kirchhoff.movies.data.ui.core.PaginatedData
import com.kirchhoff.movies.data.ui.details.person.UIPersonCredits
import com.kirchhoff.movies.data.ui.details.person.UIPersonDetails
import com.kirchhoff.movies.data.ui.main.UIPerson
import com.kirchhoff.movies.mapper.person.details.IPersonDetailsMapper
import com.kirchhoff.movies.mapper.person.main.IPersonsMapper
import com.kirchhoff.movies.network.services.PersonService
import com.kirchhoff.movies.repository.BaseRepository
import com.kirchhoff.movies.repository.Result

class PersonsRepository(
    private val personService: PersonService,
    private val personMapper: IPersonsMapper,
    private val personDetailsMapper: IPersonDetailsMapper
) : BaseRepository(), IPersonsRepository {

    override suspend fun fetchPopularPersons(page: Int): Result<PaginatedData<UIPerson>> =
        personMapper.createUIPersons(apiCall {
            personService.fetchPopularPerson(page)
        })

    override suspend fun fetchPersonDetail(personId: Int): Result<UIPersonDetails> =
        personDetailsMapper.createUIPersonDetails(apiCall {
            personService.fetchPersonDetail(
                personId
            )
        })

    override suspend fun fetchPersonCredits(personId: Int): Result<UIPersonCredits> =
        personDetailsMapper.createUIPersonCredits(apiCall {
            personService.fetchPersonCredits(
                personId
            )
        })
}
