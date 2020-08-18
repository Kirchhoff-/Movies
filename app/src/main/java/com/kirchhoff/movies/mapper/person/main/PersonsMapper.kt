package com.kirchhoff.movies.mapper.person.main

import com.kirchhoff.movies.data.network.main.NetworkPerson
import com.kirchhoff.movies.data.network.main.NetworkPersons
import com.kirchhoff.movies.data.ui.core.PaginatedData
import com.kirchhoff.movies.data.ui.main.UIPerson
import com.kirchhoff.movies.mapper.BaseMapper
import com.kirchhoff.movies.repository.Result

class PersonsMapper : BaseMapper(), IPersonsMapper {
    override fun createUIPersons(persons: Result<NetworkPersons>): Result<PaginatedData<UIPerson>> =
        when (persons) {
            is Result.Success -> Result.Success(createUIPersons(persons.data))
            else -> mapErrorOrException(persons)
        }

    private fun createUIPersons(response: NetworkPersons) =
        PaginatedData(
            response.page,
            response.results.map { createUIPerson(it) },
            response.total_pages
        )

    private fun createUIPerson(person: NetworkPerson) =
        UIPerson(
            person.id,
            person.name,
            person.profile_path
        )
}
