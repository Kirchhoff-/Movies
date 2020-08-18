package com.kirchhoff.movies.mapper.person.main

import com.kirchhoff.movies.data.network.main.NetworkPersons
import com.kirchhoff.movies.data.ui.core.PaginatedData
import com.kirchhoff.movies.data.ui.main.UIPerson
import com.kirchhoff.movies.repository.Result

interface IPersonsMapper {
    fun createUIPersons(persons: Result<NetworkPersons>): Result<PaginatedData<UIPerson>>
}
