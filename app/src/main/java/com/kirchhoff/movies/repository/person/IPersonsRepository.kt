package com.kirchhoff.movies.repository.person

import com.kirchhoff.movies.data.PersonsResponse
import com.kirchhoff.movies.repository.Result

interface IPersonsRepository {
    suspend fun fetchPopularPersons(page: Int): Result<PersonsResponse>
}
