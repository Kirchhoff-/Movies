package com.kirchhoff.movies.ui.screens.main.tvs

import com.kirchhoff.movies.data.ui.main.UIDiscoverTvs
import com.kirchhoff.movies.repository.discover.IDiscoverRepository
import com.kirchhoff.movies.ui.screens.core.PaginatedScreenVM

class TvsVM(private val discoverRepository: IDiscoverRepository) : PaginatedScreenVM<UIDiscoverTvs>() {
    override suspend fun loadData(page: Int, dataId: Int) = discoverRepository.fetchTvs(page)
}
