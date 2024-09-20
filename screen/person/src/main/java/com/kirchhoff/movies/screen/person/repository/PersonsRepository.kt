package com.kirchhoff.movies.screen.person.repository

import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.person.mapper.IPersonsMapper
import com.kirchhoff.movies.screen.person.network.PersonService

internal interface IPersonsRepository {
    suspend fun fetchPopularPersons(page: Int): Result<UIPaginated<UIPerson>>
}

internal class PersonsRepository(
    private val personService: PersonService,
    private val personMapper: IPersonsMapper
) : BaseRepository(), IPersonsRepository {

    override suspend fun fetchPopularPersons(page: Int): Result<UIPaginated<UIPerson>> =
        personMapper.createUIPersons(
            apiCall {
                personService.fetchPopularPerson(page)
            }
        )
}
