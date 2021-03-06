package com.kirchhoff.movies.mapper.person.main

import com.kirchhoff.movies.data.network.core.NetworkPaginated
import com.kirchhoff.movies.data.network.main.NetworkPerson
import com.kirchhoff.movies.data.ui.core.UIPaginated
import com.kirchhoff.movies.data.ui.main.UIPerson
import com.kirchhoff.movies.mapper.BaseMapper
import com.kirchhoff.movies.repository.Result

class PersonsMapper : BaseMapper(), IPersonsMapper {
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
