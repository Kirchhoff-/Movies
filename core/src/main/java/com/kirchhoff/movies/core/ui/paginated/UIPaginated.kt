package com.kirchhoff.movies.core.ui.paginated

data class UIPaginated<T>(val page: Int, val results: List<T>, val totalPages: Int)
