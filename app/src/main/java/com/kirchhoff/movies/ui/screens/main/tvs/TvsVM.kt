package com.kirchhoff.movies.ui.screens.main.tvs

import com.kirchhoff.movies.data.ui.core.PaginatedData
import com.kirchhoff.movies.data.ui.main.UITv
import com.kirchhoff.movies.repository.discover.IDiscoverRepository
import com.kirchhoff.movies.ui.screens.core.PaginatedScreenVM

class TvsVM(private val discoverRepository: IDiscoverRepository) : PaginatedScreenVM<PaginatedData<UITv>>() {
    override suspend fun loadData(page: Int, dataId: Int) = discoverRepository.fetchTvs(page)
}
