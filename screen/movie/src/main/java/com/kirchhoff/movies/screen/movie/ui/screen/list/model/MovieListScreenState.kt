package com.kirchhoff.movies.screen.movie.ui.screen.list.model

import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.utils.StringValue

internal data class MovieListScreenState(
    val movieList: List<UIMovie>,
    val title: StringValue,
    val errorMessage: String,
    val toolbarVisible: Boolean,
    val loadingVisible: Boolean,
    val paginationVisible: Boolean
)
