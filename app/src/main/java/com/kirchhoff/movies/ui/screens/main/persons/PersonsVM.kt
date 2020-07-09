package com.kirchhoff.movies.ui.screens.main.persons

import com.kirchhoff.movies.data.responses.PersonsResponse
import com.kirchhoff.movies.repository.person.IPersonsRepository
import com.kirchhoff.movies.ui.screens.main.MainScreenVM

class PersonsVM(private val personRepository: IPersonsRepository) :
    MainScreenVM<PersonsResponse>() {
    override suspend fun loadData(page: Int) = personRepository.fetchPopularPersons(page)
}
