package com.kirchhoff.movies.screen.movie.ui.screen.discover.usecase

import com.kirchhoff.movies.core.data.ui.UIMovie
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import com.kirchhoff.movies.screen.movie.ui.screen.discover.mapper.IMovieDiscoverMapper
import com.kirchhoff.movies.screen.movie.ui.screen.discover.repository.IMovieDiscoverRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

internal interface IMovieDiscoverUseCase {
    suspend fun discoverMovies(): Result

    data class Result(
        val nowPlaying: List<UIMovie>,
        val popular: List<UIMovie>,
        val topRated: List<UIMovie>,
        val upcoming: List<UIMovie>
    )
}

internal class MovieDiscoverUseCase(
    private val movieDiscoverRepository: IMovieDiscoverRepository,
    private val movieDiscoverMapper: IMovieDiscoverMapper
) : IMovieDiscoverUseCase {

    override suspend fun discoverMovies(): IMovieDiscoverUseCase.Result {
        var nowPlaying = emptyList<UIMovie>()
        var popular = emptyList<UIMovie>()
        var topRated = emptyList<UIMovie>()
        var upcoming = emptyList<UIMovie>()

        coroutineScope {
            launch { nowPlaying = movieDiscoverRepository.nowPlaying().toListOrEmpty() }
            launch { popular = movieDiscoverRepository.popular().toListOrEmpty() }
            launch { topRated = movieDiscoverRepository.topRated().toListOrEmpty() }
            launch { upcoming = movieDiscoverRepository.upcoming().toListOrEmpty() }
        }

        return IMovieDiscoverUseCase.Result(
            nowPlaying = nowPlaying,
            popular = popular,
            topRated = topRated,
            upcoming = upcoming
        )
    }

    private fun Result<NetworkPaginated<NetworkMovie>>.toListOrEmpty(): List<UIMovie> = when (this) {
        is Result.Success -> movieDiscoverMapper.mapMovieList(this.data)
        else -> emptyList()
    }
}
