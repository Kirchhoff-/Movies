package com.kirchhoff.movies.data.responses

import com.kirchhoff.movies.data.Movie
import com.kirchhoff.movies.data.Person
import com.kirchhoff.movies.data.Tv

data class DiscoverMoviesResponse(
    override val page: Int,
    override val results: List<Movie>,
    override val total_results: Int,
    override val total_pages: Int
) : PaginatedResponse<Movie>

data class DiscoverTvsResponse(
    override val page: Int,
    override val results: List<Tv>,
    override val total_results: Int,
    override val total_pages: Int
) : PaginatedResponse<Tv>

data class PersonsResponse(
    override val page: Int,
    override val results: List<Person>,
    override val total_results: Int,
    override val total_pages: Int
) : PaginatedResponse<Person>
