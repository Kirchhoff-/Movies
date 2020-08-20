package com.kirchhoff.movies.data.network.core

data class NetworkPaginated<T>(val page: Int, val results: List<T>, val total_pages: Int)
