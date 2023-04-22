package com.kirchhoff.movies.mapper.person.main

import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkPerson

interface IPersonsMapper {
    fun createUIPersons(persons: Result<NetworkPaginated<NetworkPerson>>): Result<UIPaginated<UIPerson>>
}
