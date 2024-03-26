package com.kirchhoff.movies.screen.movie.ui.screen.discover.usecase

import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.ui.paginated.UIPaginated

internal interface IMovieDiscoverUseCase {
    suspend fun fetchNowPlaying(page: Int): Result<UIPaginated<UIMovie>>
    suspend fun fetchPopular(page: Int): Result<UIPaginated<UIMovie>>
}

class MovieDiscoverUseCase {
}
