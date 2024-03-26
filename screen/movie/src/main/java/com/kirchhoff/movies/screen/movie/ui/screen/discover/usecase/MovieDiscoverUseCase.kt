package com.kirchhoff.movies.screen.movie.ui.screen.discover.usecase

import com.kirchhoff.movies.core.data.UIMovie
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
        val results = ArrayList<Result<NetworkPaginated<NetworkMovie>>>(4)

        coroutineScope {
            launch { results[0] = movieDiscoverRepository.nowPlaying(1) }
            launch { results[1] = movieDiscoverRepository.popular(1) }
            launch { results[2] = movieDiscoverRepository.topRated(1) }
            launch { results[3] = movieDiscoverRepository.upcoming(1) }
        }

        return IMovieDiscoverUseCase.Result(
            nowPlaying = results[0].toListOrEmpty(),
            popular = results[1].toListOrEmpty(),
            topRated = results[2].toListOrEmpty(),
            upcoming = results[3].toListOrEmpty()
        )
    }

    private fun Result<NetworkPaginated<NetworkMovie>>.toListOrEmpty(): List<UIMovie> = when (this) {
        is Result.Success -> movieDiscoverMapper.mapMovieList(this.data)
        else -> emptyList()
    }
}
