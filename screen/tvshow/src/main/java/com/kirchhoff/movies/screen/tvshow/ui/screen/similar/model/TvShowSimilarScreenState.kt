package com.kirchhoff.movies.screen.tvshow.ui.screen.similar.model

import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.utils.StringValue

internal data class TvShowSimilarScreenState(
    val tvShowList: List<UITv>,
    val title: StringValue,
    val errorMessage: String,
    val loadingVisible: Boolean,
    val paginationVisible: Boolean,
    val tvShowListVisible: Boolean,
    val emptyTextVisible: Boolean
)
