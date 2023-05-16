package com.kirchhoff.movies.screen.similar.repository

import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.mapper.IDiscoverMapper
import com.kirchhoff.movies.core.repository.BaseRepository
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.similar.network.SimilarService

class SimilarRepository(
    private val similarService: SimilarService,
    private val discoverMapper: IDiscoverMapper
) : BaseRepository(), ISimilarRepository {
    override suspend fun fetchMovies(movieId: Int, page: Int): Result<UIPaginated<UIMovie>> =
        discoverMapper.createUIDiscoverMovieList(apiCall {
            similarService.fetchMovies(movieId, page)
        })

    override suspend fun fetchTvs(tvId: Int, page: Int): Result<UIPaginated<UITv>> =
        discoverMapper.createUIDiscoverTvList(apiCall {
            similarService.fetchTvs(tvId, page)
        })
}
