package com.kirchhoff.movies.screen.credits.ui.screen.cast.usecase

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.core.mapper.ICoreMapper
import com.kirchhoff.movies.storage.movie.IStorageMovie

internal interface ICreditsCastUseCase {
    fun actorsList(movieId: MovieId): List<UIEntertainmentPerson.Actor>
}

internal class CreditsCastUseCase(
    private val movieStorage: IStorageMovie,
    private val coreMapper: ICoreMapper
) : ICreditsCastUseCase {

    override fun actorsList(movieId: MovieId): List<UIEntertainmentPerson.Actor> {
        val actors = movieStorage.credits(movieId.value)?.cast ?: error(
            "There is no info about actors for movie with id = ${movieId.value}"
        )

        return coreMapper.createUIEntertainmentActors(actors)
    }
}
