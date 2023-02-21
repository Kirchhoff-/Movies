package com.kirchhoff.movies.ui.screens.main.tvs

import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.data.ui.main.UITv
import com.kirchhoff.movies.repository.discover.IDiscoverRepository
import com.kirchhoff.movies.ui.screens.core.PaginatedScreenVM

class TvsVM(private val discoverRepository: IDiscoverRepository) : PaginatedScreenVM<UIPaginated<UITv>>() {
    override suspend fun loadData(page: Int) = discoverRepository.fetchTvs(page)
}
