package com.kirchhoff.movies.data

data class PersonsResponse(
    val page: Int,
    val results: List<Person>,
    val total_results: Int,
    val total_pages: Int
)
