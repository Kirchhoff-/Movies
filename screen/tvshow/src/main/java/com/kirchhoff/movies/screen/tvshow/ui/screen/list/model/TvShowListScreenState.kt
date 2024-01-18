package com.kirchhoff.movies.screen.tvshow.ui.screen.list.model

import com.kirchhoff.movies.core.data.UITv

internal data class TvShowListScreenState(
    val tvShowList: List<UITv>,
    val errorMessage: String,
    val loadingVisible: Boolean,
    val paginationVisible: Boolean,
    val tvShowListVisible: Boolean,
    val emptyTextVisible: Boolean
)
