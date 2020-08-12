package com.kirchhoff.movies.data.ui.main

import com.kirchhoff.movies.data.Person
import com.kirchhoff.movies.data.responses.PaginatedResponse

class UIPersons(
    override val page: Int,
    override val results: List<Person>,
    override val total_results: Int,
    override val total_pages: Int
) : PaginatedResponse<Person>
