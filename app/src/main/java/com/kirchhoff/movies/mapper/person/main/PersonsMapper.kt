package com.kirchhoff.movies.mapper.person.main

import com.kirchhoff.movies.data.network.main.NetworkPersons
import com.kirchhoff.movies.data.ui.main.UIPersons
import com.kirchhoff.movies.mapper.BaseMapper
import com.kirchhoff.movies.repository.Result

class PersonsMapper : BaseMapper(), IPersonsMapper {
    override fun createUIPersons(persons: Result<NetworkPersons>): Result<UIPersons> =
        when (persons) {
            is Result.Success -> Result.Success(createUIPersons(persons.data))
            else -> mapErrorOrException(persons)
        }

    private fun createUIPersons(response: NetworkPersons) =
        UIPersons(
            response.page,
            response.results,
            response.total_results,
            response.total_pages
        )
}
