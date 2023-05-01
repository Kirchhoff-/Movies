package com.kirchhoff.movies.screen.person.ui.screen.list

import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenVM
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.person.repository.IPersonsRepository

class PersonsVM(private val personRepository: IPersonsRepository) : PaginatedScreenVM<UIPaginated<UIPerson>>() {
    override suspend fun loadData(page: Int) = personRepository.fetchPopularPersons(page)
}
