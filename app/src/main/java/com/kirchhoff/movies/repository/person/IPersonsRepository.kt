package com.kirchhoff.movies.repository.person

import com.kirchhoff.movies.data.ui.details.person.UIPersonCredits
import com.kirchhoff.movies.data.ui.details.person.UIPersonDetails
import com.kirchhoff.movies.data.ui.main.UIPersons
import com.kirchhoff.movies.repository.Result

interface IPersonsRepository {
    suspend fun fetchPopularPersons(page: Int): Result<UIPersons>
    suspend fun fetchPersonDetail(personId: Int): Result<UIPersonDetails>
    suspend fun fetchPersonCredits(personId: Int): Result<UIPersonCredits>
}
