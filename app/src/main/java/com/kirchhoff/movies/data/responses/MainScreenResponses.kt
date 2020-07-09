package com.kirchhoff.movies.data.responses

import com.kirchhoff.movies.data.Movie
import com.kirchhoff.movies.data.Person
import com.kirchhoff.movies.data.Tv

data class DiscoverMoviesResponse(
    val page: Int,
    val results: List<Movie>,
    val total_results: Int,
    val total_pages: Int
)

data class DiscoverTvsResponse(
    val page: Int,
    val results: List<Tv>,
    val total_results: Int,
    val total_pages: Int
)

data class PersonsResponse(
    val page: Int,
    val results: List<Person>,
    val total_results: Int,
    val total_pages: Int
)
