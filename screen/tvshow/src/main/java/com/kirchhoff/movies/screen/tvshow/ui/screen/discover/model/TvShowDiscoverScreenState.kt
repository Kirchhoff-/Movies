package com.kirchhoff.movies.screen.tvshow.ui.screen.discover.model

import com.kirchhoff.movies.core.data.ui.UITv

internal data class TvShowDiscoverScreenState(
    val airingToday: List<UITv>,
    val onTheAir: List<UITv>,
    val popular: List<UITv>,
    val topRated: List<UITv>,
    val isLoading: Boolean
) {
    companion object {
        val Default = TvShowDiscoverScreenState(
            airingToday = emptyList(),
            onTheAir = emptyList(),
            popular = emptyList(),
            topRated = emptyList(),
            isLoading = true
        )
    }
}
