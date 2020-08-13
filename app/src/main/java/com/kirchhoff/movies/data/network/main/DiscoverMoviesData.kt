package com.kirchhoff.movies.data.network.main

import com.kirchhoff.movies.data.responses.PaginatedResponse

data class NetworkDiscoverMovies(
    override val page: Int,
    override val results: List<NetworkMovie>,
    override val total_results: Int,
    override val total_pages: Int
) : PaginatedResponse<NetworkMovie>

data class NetworkMovie(
    val id: Int,
    val title: String,
    val poster_path: String?,
    val backdrop_path: String?
)
