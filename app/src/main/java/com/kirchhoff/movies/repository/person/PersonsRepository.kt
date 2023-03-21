package com.kirchhoff.movies.repository.person

import app.moviebase.tmdb.Tmdb3
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.data.ui.details.person.UIPersonCredits
import com.kirchhoff.movies.data.ui.details.person.UIPersonDetails
import com.kirchhoff.movies.data.ui.main.UIPerson
import com.kirchhoff.movies.mapper.person.details.IPersonDetailsMapper
import com.kirchhoff.movies.mapper.person.main.IPersonsMapper
import com.kirchhoff.movies.network.services.PersonService

class PersonsRepository(
    private val personService: PersonService,
    private val personMapper: IPersonsMapper,
    private val personDetailsMapper: IPersonDetailsMapper,
    private val tmdbApi: Tmdb3
) : BaseRepository(), IPersonsRepository {

    override suspend fun fetchPopularPersons(page: Int): Result<UIPaginated<UIPerson>> =
        personMapper.createUIPersons(apiCall {
            personService.fetchPopularPerson(page)
        })

    override suspend fun fetchPersonDetail(personId: Int): Result<UIPersonDetails> =
        personDetailsMapper.createUIPersonDetails(tmdbApiCall { tmdbApi.people.getDetails(personId) })

    override suspend fun fetchPersonCredits(personId: Int): Result<UIPersonCredits> =
        personDetailsMapper.createUIPersonCredits(apiCall {
            personService.fetchPersonCredits(
                personId
            )
        })
}
