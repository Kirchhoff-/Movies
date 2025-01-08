package com.kirchhoff.movies.screen.movie.ui.screen.images.model

import com.kirchhoff.movies.core.data.ui.UIImage

internal data class MovieImagesScreenState(
    val title: String,
    val image: List<UIImage>
) {
    companion object {
        val Default = MovieImagesScreenState(
            title = "",
            image = emptyList()
        )
    }
}
