package com.kirchhoff.movies.screen.credits.ui.screen.crew.usecase

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.mapper.ICoreMapper
import com.kirchhoff.movies.screen.credits.ui.screen.crew.factory.ICreditsCrewListFactory
import com.kirchhoff.movies.screen.credits.ui.screen.crew.model.CreditsCrewListItem
import com.kirchhoff.movies.storage.movie.IStorageMovie

internal interface ICreditsCrewUseCase {
    fun createCrewList(movieId: MovieId, expandedItems: Set<String>): List<CreditsCrewListItem>
}

internal class CreditsCrewUseCase(
    private val movieStorage: IStorageMovie,
    private val coreMapper: ICoreMapper,
    private val creditsCrewListFactory: ICreditsCrewListFactory
) : ICreditsCrewUseCase {

    override fun createCrewList(movieId: MovieId, expandedItems: Set<String>): List<CreditsCrewListItem> {
        val creators = movieStorage.credits(movieId.value)?.crew ?: error(
            "There is no info about creators for movie with id = ${movieId.value}"
        )

        return creditsCrewListFactory.createCrewList(
            coreMapper.createUIEntertainmentCreators(creators),
            expandedItems
        )
    }
}
