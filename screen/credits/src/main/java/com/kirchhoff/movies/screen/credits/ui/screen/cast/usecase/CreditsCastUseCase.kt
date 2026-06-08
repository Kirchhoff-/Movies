package com.kirchhoff.movies.screen.credits.ui.screen.cast.usecase

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.ui.UIEntertainmentPerson
import com.kirchhoff.movies.core.mapper.CoreMapper
import com.kirchhoff.movies.storage.movie.StorageMovie

internal class CreditsCastUseCase(
    private val movieStorage: StorageMovie,
    private val coreMapper: CoreMapper
) {

    fun actorsList(movieId: MovieId): List<UIEntertainmentPerson.Actor> {
        val actors = movieStorage.credits(movieId.value)?.cast ?: error(
            "There is no info about actors for movie with id = ${movieId.value}"
        )

        return coreMapper.createUIEntertainmentActors(actors)
    }
}
