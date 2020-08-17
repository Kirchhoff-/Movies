package com.kirchhoff.movies.data.network.main

data class NetworkDiscoverMovies(
    val page: Int,
    val results: List<NetworkMovie>,
    val total_pages: Int
)

data class NetworkMovie(
    val id: Int,
    val title: String,
    val poster_path: String?,
    val backdrop_path: String?
)
