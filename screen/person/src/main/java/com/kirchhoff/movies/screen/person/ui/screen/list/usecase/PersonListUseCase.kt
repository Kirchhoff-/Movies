package com.kirchhoff.movies.screen.person.ui.screen.list.usecase

import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.repository.Result
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
        when (val response = personListRepository.fetchPopularPersons(page)) {
            is Result.Success -> Result.Success(personListMapper.createUIPersons(response.data))
            else -> response.mapErrorOrException()
        }
}
