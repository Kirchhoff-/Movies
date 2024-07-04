package com.kirchhoff.movies.screen.tvshow.ui.screen.discover.model

import com.kirchhoff.movies.core.data.UITv

internal data class TvShowDiscoverScreenState(
    val airingToday: List<UITv>,
    val onTheAir: List<UITv>,
    val popular: List<UITv>,
    val topRated: List<UITv>,
    val isLoading: Boolean
)
