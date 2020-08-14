package com.kirchhoff.movies.data.network.main

import com.kirchhoff.movies.data.responses.PaginatedResponse

data class NetworkPersons(
    override val page: Int,
    override val results: List<NetworkPerson>,
    override val total_results: Int,
    override val total_pages: Int
) : PaginatedResponse<NetworkPerson>

data class NetworkPerson(
    val id: Int,
    val name: String,
    val profile_path: String?
)
