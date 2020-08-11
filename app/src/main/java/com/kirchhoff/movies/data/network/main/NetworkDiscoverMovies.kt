package com.kirchhoff.movies.data.network.main

import com.kirchhoff.movies.data.Movie
import com.kirchhoff.movies.data.responses.PaginatedResponse

data class NetworkDiscoverMovies(
    override val page: Int,
    override val results: List<Movie>,
    override val total_results: Int,
    override val total_pages: Int
) : PaginatedResponse<Movie>
