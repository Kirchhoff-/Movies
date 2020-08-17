package com.kirchhoff.movies.data.network.main

data class NetworkPersons(
    val page: Int,
    val results: List<NetworkPerson>,
    val total_pages: Int
)

data class NetworkPerson(
    val id: Int,
    val name: String,
    val profile_path: String?
)
