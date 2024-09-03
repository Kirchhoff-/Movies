package com.kirchhoff.movies.screen.movie.ui.screen.discover.model

import com.kirchhoff.movies.core.data.UIMovie

internal data class MovieDiscoverScreenState(
    val nowPlaying: List<UIMovie>,
    val popular: List<UIMovie>,
    val topRated: List<UIMovie>,
    val upcoming: List<UIMovie>,
    val isLoading: Boolean
) {
    companion object {
        val Default = MovieDiscoverScreenState(
            nowPlaying = emptyList(),
            popular = emptyList(),
            topRated = emptyList(),
            upcoming = emptyList(),
            isLoading = false
        )
    }
}
