package com.kirchhoff.movies.ui.screens.main.tvs

import com.kirchhoff.movies.data.responses.DiscoverTvsResponse
import com.kirchhoff.movies.repository.discover.IDiscoverRepository
import com.kirchhoff.movies.ui.screens.main.MainScreenVM

class TvsVM(private val discoverRepository: IDiscoverRepository) :
    MainScreenVM<DiscoverTvsResponse>() {
    override suspend fun loadData(page: Int) = discoverRepository.fetchTvs(page)
}
