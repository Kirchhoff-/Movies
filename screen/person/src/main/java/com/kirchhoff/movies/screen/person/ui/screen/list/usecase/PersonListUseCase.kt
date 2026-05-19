package com.kirchhoff.movies.screen.person.ui.screen.list.usecase

import com.kirchhoff.movies.core.data.ui.UIPerson
import com.kirchhoff.movies.core.repository.RepositoryResult
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.person.ui.screen.list.mapper.IPersonListMapper
import com.kirchhoff.movies.screen.person.ui.screen.list.repository.IPersonListRepository

internal interface IPersonListUseCase {
    suspend fun fetchPopularPersons(page: Int): Result<UIPaginated<UIPerson>>
}

internal class PersonListUseCase(
    private val personListRepository: IPersonListRepository,
    private val personListMapper: IPersonListMapper
) : IPersonListUseCase {

    override suspend fun fetchPopularPersons(page: Int): Result<UIPaginated<UIPerson>> =
        when (val response = personListRepository.popularPersons(page)) {
            is RepositoryResult.Success -> Result.success(personListMapper.createUIPersons(response.data))
            else -> Result.failure(Exception("Can't fetch the popular persons"))
        }
}
