package com.kirchhoff.movies.repository.discover

import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.data.ui.main.UIMovie
import com.kirchhoff.movies.data.ui.main.UITv
import com.kirchhoff.movies.mapper.discover.IDiscoverMapper
import com.kirchhoff.movies.network.services.DiscoverService

class DiscoverRepository(
    private val discoverService: DiscoverService,
    private val discoverMapper: IDiscoverMapper
) : BaseRepository(), IDiscoverRepository {

    override suspend fun fetchMovies(page: Int): Result<UIPaginated<UIMovie>> =
        discoverMapper.createUIDiscoverMovieList(apiCall {
            discoverService.fetchDiscoverMovie(page)
        })

    override suspend fun fetchTvs(page: Int): Result<UIPaginated<UITv>> =
        discoverMapper.createUIDiscoverTvList(apiCall {
            discoverService.fetchDiscoverTv(page)
        })
}
