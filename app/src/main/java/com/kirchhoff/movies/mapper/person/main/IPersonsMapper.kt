package com.kirchhoff.movies.mapper.person.main

import com.kirchhoff.movies.data.network.main.NetworkPersons
import com.kirchhoff.movies.data.ui.main.UIPersons
import com.kirchhoff.movies.repository.Result

interface IPersonsMapper {
    fun createUIPersons(persons: Result<NetworkPersons>): Result<UIPersons>
}
