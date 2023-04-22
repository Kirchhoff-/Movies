package com.kirchhoff.movies.repository.person

import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.data.ui.details.person.UIPersonCredits
import com.kirchhoff.movies.data.ui.details.person.UIPersonDetails
import com.kirchhoff.movies.data.ui.details.person.UIPersonImage
import com.kirchhoff.movies.mapper.person.details.IPersonDetailsMapper
import com.kirchhoff.movies.mapper.person.main.IPersonsMapper
import com.kirchhoff.movies.network.services.PersonService

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
