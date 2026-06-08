package com.kirchhoff.movies.screen.tvshow.ui.screen.discover.usecase

import com.kirchhoff.movies.core.data.ui.UITv
import com.kirchhoff.movies.core.repository.RepositoryResult
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkTv
import com.kirchhoff.movies.screen.tvshow.ui.screen.discover.mapper.TvShowDiscoverMapper
import com.kirchhoff.movies.screen.tvshow.ui.screen.discover.repository.TvShowDiscoverRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

internal class TvShowDiscoverUseCase(
    private val tvShowDiscoverRepository: TvShowDiscoverRepository,
    private val tvShowDiscoverMapper: TvShowDiscoverMapper
) {

    suspend fun discoverTvShows(): Result {
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

        return Result(
            airingToday = airingToday,
            onTheAir = onTheAir,
            popular = popular,
            topRated = topRated
        )
    }

    private fun RepositoryResult<NetworkPaginated<NetworkTv>>.toListOrEmpty(): List<UITv> = when (this) {
        is RepositoryResult.Success -> tvShowDiscoverMapper.mapTvShowList(this.data)
        else -> emptyList()
    }

    data class Result(
        val airingToday: List<UITv>,
        val onTheAir: List<UITv>,
        val popular: List<UITv>,
        val topRated: List<UITv>
    )
}
