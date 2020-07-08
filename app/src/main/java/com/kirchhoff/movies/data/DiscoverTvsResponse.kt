package com.kirchhoff.movies.data

data class DiscoverTvsResponse(
    val page: Int,
    val results: List<Tv>,
    val total_results: Int,
    val total_pages: Int
)
