package com.kirchhoff.movies.screen.tvshow.ui.screen.list.model

import com.kirchhoff.movies.core.data.ui.UITv
import com.kirchhoff.movies.core.utils.StringValue

internal data class TvShowListScreenState(
    val tvShowList: List<UITv>,
    val title: StringValue,
    val errorMessage: String,
    val loadingVisible: Boolean,
    val paginationVisible: Boolean,
    val emptyTextVisible: Boolean
) {
    companion object {
        val Default = TvShowListScreenState(
            tvShowList = emptyList(),
            title = StringValue.Empty,
            errorMessage = "",
            loadingVisible = false,
            paginationVisible = false,
            emptyTextVisible = false
        )
    }
}
