package com.kirchhoff.movies.screen.person.repository

import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.person.data.UIPersonCredits
import com.kirchhoff.movies.screen.person.data.UIPersonDetails
import com.kirchhoff.movies.screen.person.data.UIPersonImage

internal interface IPersonsRepository {
    suspend fun fetchPopularPersons(page: Int): Result<UIPaginated<UIPerson>>
    suspend fun fetchPersonDetail(personId: Int): Result<UIPersonDetails>
    suspend fun fetchPersonCredits(personId: Int): Result<UIPersonCredits>
    suspend fun fetchPersonImages(personId: Int): Result<List<UIPersonImage>>
}
