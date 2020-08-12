package com.kirchhoff.movies.repository.discover

import com.kirchhoff.movies.data.ui.main.UIDiscoverMovies
import com.kirchhoff.movies.data.ui.main.UIDiscoverTvs
import com.kirchhoff.movies.mapper.discover.IDiscoverMapper
import com.kirchhoff.movies.network.services.DiscoverService
import com.kirchhoff.movies.repository.BaseRepository
import com.kirchhoff.movies.repository.Result

class DiscoverRepository(
    private val discoverService: DiscoverService,
    private val discoverMapper: IDiscoverMapper
) : BaseRepository(), IDiscoverRepository {

    override suspend fun fetchMovies(page: Int): Result<UIDiscoverMovies> =
        discoverMapper.createUIDiscoverMovieList(apiCall {
            discoverService.fetchDiscoverMovie(page)
        })

    override suspend fun fetchTvs(page: Int): Result<UIDiscoverTvs> =
        discoverMapper.createUIDiscoverTvList(apiCall {
            discoverService.fetchDiscoverTv(page)
        })
}
