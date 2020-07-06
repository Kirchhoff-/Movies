package com.kirchhoff.movies.data

data class DiscoverMoviesResponse(
    val page: Int,
    val results: List<Movie>,
    val total_results: Int,
    val total_pages: Int
)