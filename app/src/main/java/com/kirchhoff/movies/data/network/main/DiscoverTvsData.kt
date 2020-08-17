package com.kirchhoff.movies.data.network.main

data class NetworkDiscoverTvs(
    val page: Int,
    val results: List<NetworkTv>,
    val total_pages: Int
)

data class NetworkTv(
    val id: Int,
    val poster_path: String?,
    val backdrop_path: String?,
    val name: String
)
