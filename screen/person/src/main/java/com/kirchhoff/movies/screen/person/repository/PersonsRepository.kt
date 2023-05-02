package com.kirchhoff.movies.screen.person.repository

import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.person.data.UIPersonCredits
import com.kirchhoff.movies.screen.person.data.UIPersonDetails
import com.kirchhoff.movies.screen.person.data.UIPersonImage
import com.kirchhoff.movies.screen.person.mapper.details.IPersonDetailsMapper
import com.kirchhoff.movies.screen.person.mapper.main.IPersonsMapper
import com.kirchhoff.movies.screen.person.network.PersonService

class PersonsRepository(
    private val personService: PersonService,
    private val personMapper: IPersonsMapper,
    private val personDetailsMapper: IPersonDetailsMapper
) : BaseRepository(), IPersonsRepository {

    override suspend fun fetchPopularPersons(page: Int): Result<UIPaginated<UIPerson>> =
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

    override suspend fun fetchPersonImages(personId: Int): Result<List<UIPersonImage>> =
        personDetailsMapper.createUIPersonImages(apiCall {
            personService.fetchPersonImages(personId)
        })
}
