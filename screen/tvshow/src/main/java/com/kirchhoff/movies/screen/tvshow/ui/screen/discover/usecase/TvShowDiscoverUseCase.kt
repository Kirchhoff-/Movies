package com.kirchhoff.movies.screen.tvshow.ui.screen.discover.usecase

import com.kirchhoff.movies.core.data.ui.UITv
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkTv
import com.kirchhoff.movies.screen.tvshow.ui.screen.discover.mapper.ITvShowDiscoverMapper
import com.kirchhoff.movies.screen.tvshow.ui.screen.discover.repository.ITvShowDiscoverRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

internal interface ITvShowDiscoverUseCase {
    suspend fun discoverTvShows(): Result

    data class Result(
        val airingToday: List<UITv>,
        val onTheAir: List<UITv>,
        val popular: List<UITv>,
        val topRated: List<UITv>
    )
}

internal class TvShowDiscoverUseCase(
    private val tvShowDiscoverRepository: ITvShowDiscoverRepository,
    private val tvShowDiscoverMapper: ITvShowDiscoverMapper
) : ITvShowDiscoverUseCase {

    override suspend fun discoverTvShows(): ITvShowDiscoverUseCase.Result {
        var airingToday = emptyList<UITv>()
        var onTheAir = emptyList<UITv>()
        var popular = emptyList<UITv>()
        var topRated = emptyList<UITv>()

        coroutineScope {
            launch { airingToday = tvShowDiscoverRepository.airingToday().toListOrEmpty() }
            launch { onTheAir = tvShowDiscoverRepository.onTheAir().toListOrEmpty() }
            launch { popular = tvShowDiscoverRepository.popular().toListOrEmpty() }
            launch { topRated = tvShowDiscoverRepository.topRated().toListOrEmpty() }
        }

        return ITvShowDiscoverUseCase.Result(
            airingToday = airingToday,
            onTheAir = onTheAir,
            popular = popular,
            topRated = topRated
        )
    }

    private fun Result<NetworkPaginated<NetworkTv>>.toListOrEmpty(): List<UITv> = when (this) {
        is Result.Success -> tvShowDiscoverMapper.mapTvShowList(this.data)
        else -> emptyList()
    }
}
