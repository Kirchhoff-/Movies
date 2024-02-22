package com.kirchhoff.movies.screen.person.mapper

import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.mapper.BaseMapper
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkPerson

internal interface IPersonsMapper {
    fun createUIPersons(persons: Result<NetworkPaginated<NetworkPerson>>): Result<UIPaginated<UIPerson>>
}

internal class PersonsMapper : BaseMapper(), IPersonsMapper {
    override fun createUIPersons(persons: Result<NetworkPaginated<NetworkPerson>>): Result<UIPaginated<UIPerson>> =
        when (persons) {
            is Result.Success -> Result.Success(createUIPersons(persons.data))
            else -> mapErrorOrException(persons)
        }

    private fun createUIPersons(response: NetworkPaginated<NetworkPerson>) =
        UIPaginated(
            response.page,
            response.results.map { createUIPerson(it) },
            response.totalPages
        )

    private fun createUIPerson(person: NetworkPerson) =
        UIPerson(
            person.id,
            person.name,
            person.profilePath
        )
}
