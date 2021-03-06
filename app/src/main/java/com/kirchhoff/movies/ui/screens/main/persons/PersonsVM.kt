package com.kirchhoff.movies.ui.screens.main.persons

import com.kirchhoff.movies.data.ui.core.UIPaginated
import com.kirchhoff.movies.data.ui.main.UIPerson
import com.kirchhoff.movies.repository.person.IPersonsRepository
import com.kirchhoff.movies.ui.screens.core.PaginatedScreenVM

class PersonsVM(private val personRepository: IPersonsRepository) : PaginatedScreenVM<UIPaginated<UIPerson>>() {
    override suspend fun loadData(page: Int) = personRepository.fetchPopularPersons(page)
}
