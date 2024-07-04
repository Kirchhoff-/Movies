package com.kirchhoff.movies.screen.tvshow.ui.screen.list.model

import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.utils.StringValue

internal data class TvShowListScreenState(
    val tvShowList: List<UITv>,
    val title: StringValue,
    val errorMessage: String,
    val loadingVisible: Boolean,
    val paginationVisible: Boolean,
    val emptyTextVisible: Boolean
)
