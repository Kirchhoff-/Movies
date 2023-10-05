package com.kirchhoff.movies.screen.movie.ui.screen.list.model

import androidx.annotation.StringRes
import com.kirchhoff.movies.core.data.UIMovie

internal data class MovieListScreenState(
    val movieList: List<UIMovie>,
    @StringRes val titleId: Int,
    val titleArgs: Any,
    val errorMessage: String,
    val loadingVisible: Boolean,
    val paginationVisible: Boolean
)
