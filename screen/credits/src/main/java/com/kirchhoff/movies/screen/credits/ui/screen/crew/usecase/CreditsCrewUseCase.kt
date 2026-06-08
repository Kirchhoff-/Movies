package com.kirchhoff.movies.screen.credits.ui.screen.crew.usecase

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.mapper.CoreMapper
import com.kirchhoff.movies.screen.credits.ui.screen.crew.factory.CreditsCrewListFactory
import com.kirchhoff.movies.screen.credits.ui.screen.crew.model.CreditsCrewListItem
import com.kirchhoff.movies.storage.movie.StorageMovie

internal class CreditsCrewUseCase(
    private val movieStorage: StorageMovie,
    private val coreMapper: CoreMapper,
    private val creditsCrewListFactory: CreditsCrewListFactory
) {

    fun createCrewList(movieId: MovieId, expandedItems: Set<String>): List<CreditsCrewListItem> {
        val creators = movieStorage.credits(movieId.value)?.crew ?: error(
            "There is no info about creators for movie with id = ${movieId.value}"
        )

        return creditsCrewListFactory.createCrewList(
            coreMapper.createUIEntertainmentCreators(creators),
            expandedItems
        )
    }
}
