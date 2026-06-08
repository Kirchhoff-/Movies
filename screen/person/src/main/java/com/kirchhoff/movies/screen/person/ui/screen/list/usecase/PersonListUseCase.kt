package com.kirchhoff.movies.screen.person.ui.screen.list.usecase

import com.kirchhoff.movies.core.data.ui.UIPerson
import com.kirchhoff.movies.core.repository.RepositoryResult
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.person.ui.screen.list.mapper.PersonListMapper
import com.kirchhoff.movies.screen.person.ui.screen.list.repository.PersonListRepository

internal class PersonListUseCase(
    private val personListRepository: PersonListRepository,
    private val personListMapper: PersonListMapper
) {

    suspend fun fetchPopularPersons(page: Int): Result<UIPaginated<UIPerson>> =
        when (val response = personListRepository.popularPersons(page)) {
            is RepositoryResult.Success -> Result.success(personListMapper.createUIPersons(response.data))
            else -> Result.failure(Exception("Can't fetch the popular persons"))
        }
}
