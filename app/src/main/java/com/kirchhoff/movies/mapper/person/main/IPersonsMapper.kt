package com.kirchhoff.movies.mapper.person.main

import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.data.ui.main.UIPerson
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkPerson
import com.kirchhoff.movies.repository.Result

interface IPersonsMapper {
    fun createUIPersons(persons: Result<NetworkPaginated<NetworkPerson>>): Result<UIPaginated<UIPerson>>
}
