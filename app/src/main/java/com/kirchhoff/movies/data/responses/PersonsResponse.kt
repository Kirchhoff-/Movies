package com.kirchhoff.movies.data.responses

import com.kirchhoff.movies.data.Person

data class PersonsResponse(
    override val page: Int,
    override val results: List<Person>,
    override val total_results: Int,
    override val total_pages: Int
) : PaginatedResponse<Person>
