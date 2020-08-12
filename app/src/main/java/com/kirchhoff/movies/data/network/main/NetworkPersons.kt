package com.kirchhoff.movies.data.network.main

import com.kirchhoff.movies.data.Person
import com.kirchhoff.movies.data.responses.PaginatedResponse

data class NetworkPersons(
    override val page: Int,
    override val results: List<Person>,
    override val total_results: Int,
    override val total_pages: Int
) : PaginatedResponse<Person>
