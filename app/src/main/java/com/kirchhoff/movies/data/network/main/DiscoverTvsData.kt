package com.kirchhoff.movies.data.network.main

import com.kirchhoff.movies.data.responses.PaginatedResponse

data class NetworkDiscoverTvs(
    override val page: Int,
    override val results: List<NetworkTv>,
    override val total_results: Int,
    override val total_pages: Int
) : PaginatedResponse<NetworkTv>

data class NetworkTv(
    val id: Int,
    val poster_path: String?,
    val backdrop_path: String?,
    val name: String
)
