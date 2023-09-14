package com.kirchhoff.movies.screen.movie.ui.screen.genre.model

import com.kirchhoff.movies.core.data.UIMovie

internal data class MovieListByGenreScreenState(
    val movieList: List<UIMovie>,
    val genre: String,
    val errorMessage: String,
    val loadingVisible: Boolean,
    val paginationVisible: Boolean
)
