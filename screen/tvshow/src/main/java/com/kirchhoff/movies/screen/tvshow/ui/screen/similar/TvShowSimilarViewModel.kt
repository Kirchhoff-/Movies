package com.kirchhoff.movies.screen.tvshow.ui.screen.similar

import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenViewModel
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.tvshow.repository.ITvShowRepository

class TvShowSimilarViewModel(
    private val tvId: Int,
    private val tvShowRepository: ITvShowRepository
) : PaginatedScreenViewModel<UIPaginated<UITv>>() {
    override suspend fun loadData(page: Int) = tvShowRepository.fetchSimilarTvShows(tvId, page)
}
