package com.kirchhoff.movies.repository.person

import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.data.ui.details.person.UIPersonCredits
import com.kirchhoff.movies.data.ui.details.person.UIPersonDetails
import com.kirchhoff.movies.data.ui.details.person.UIPersonImage

interface IPersonsRepository {
    suspend fun fetchPopularPersons(page: Int): Result<UIPaginated<UIPerson>>
    suspend fun fetchPersonDetail(personId: Int): Result<UIPersonDetails>
    suspend fun fetchPersonCredits(personId: Int): Result<UIPersonCredits>
    suspend fun fetchPersonImages(personId: Int): Result<List<UIPersonImage>>
}
