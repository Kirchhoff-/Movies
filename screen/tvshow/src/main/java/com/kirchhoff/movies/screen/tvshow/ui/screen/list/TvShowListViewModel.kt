package com.kirchhoff.movies.screen.tvshow.ui.screen.list

import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenViewModel
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.tvshow.repository.ITvShowRepository

internal class TvShowListViewModel(private val discoverRepository: ITvShowRepository) : PaginatedScreenViewModel<UIPaginated<UITv>>() {
    override suspend fun loadData(page: Int) = discoverRepository.fetchDiscoverList(page)
}
