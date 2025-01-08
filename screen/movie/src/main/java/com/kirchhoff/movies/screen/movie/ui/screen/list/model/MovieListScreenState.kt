package com.kirchhoff.movies.screen.movie.ui.screen.list.model

import com.kirchhoff.movies.core.data.ui.UIMovie
import com.kirchhoff.movies.core.utils.StringValue

internal data class MovieListScreenState(
    val movieList: List<UIMovie>,
    val title: StringValue,
    val errorMessage: String,
    val loadingVisible: Boolean,
    val paginationVisible: Boolean
) {
    companion object {
        val Default = MovieListScreenState(
            movieList = emptyList(),
            title = StringValue.Empty,
            errorMessage = "",
            loadingVisible = false,
            paginationVisible = false
        )
    }
}
