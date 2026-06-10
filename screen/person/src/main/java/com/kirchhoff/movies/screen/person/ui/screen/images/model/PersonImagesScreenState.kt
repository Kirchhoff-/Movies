package com.kirchhoff.movies.screen.person.ui.screen.images.model

import com.kirchhoff.movies.screen.person.data.PersonUIImage

internal data class PersonImagesScreenState(
    val imagesUrls: List<PersonUIImage>,
    val startPosition: Int
) {
    companion object {
        val Default = PersonImagesScreenState(
            imagesUrls = emptyList(),
            startPosition = 0
        )
    }
}
