package com.kirchhoff.movies.data.responses

interface PaginatedResponse<T> {
    val page: Int
    val results: List<T>
    val total_results: Int
    val total_pages: Int
}
