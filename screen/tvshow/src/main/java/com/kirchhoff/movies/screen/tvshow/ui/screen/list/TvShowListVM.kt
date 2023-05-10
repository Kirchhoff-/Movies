package com.kirchhoff.movies.screen.tvshow.ui.screen.list

import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenVM
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.tvshow.repository.ITvShowRepository

class TvShowListVM(private val discoverRepository: ITvShowRepository) : PaginatedScreenVM<UIPaginated<UITv>>() {
    override suspend fun loadData(page: Int) = discoverRepository.fetchDiscoverList(page)
}
