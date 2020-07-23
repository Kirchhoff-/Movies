package com.kirchhoff.movies.ui.screens.main.persons

import com.kirchhoff.movies.data.responses.PersonsResponse
import com.kirchhoff.movies.repository.person.IPersonsRepository
import com.kirchhoff.movies.ui.screens.core.PaginatedScreenVM

class PersonsVM(private val personRepository: IPersonsRepository) :
    PaginatedScreenVM<PersonsResponse>() {
    override suspend fun loadData(page: Int, dataId: Int) = personRepository.fetchPopularPersons(page)
}
