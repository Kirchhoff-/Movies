package com.kirchhoff.movies.data.ui.core

interface Paginated<T> {
    val page: Int
    val results: List<T>
    val totalPages: Int
}
