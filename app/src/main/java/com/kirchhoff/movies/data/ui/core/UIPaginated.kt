package com.kirchhoff.movies.data.ui.core

data class UIPaginated<T>(val page: Int, val results: List<T>, val totalPages: Int)
