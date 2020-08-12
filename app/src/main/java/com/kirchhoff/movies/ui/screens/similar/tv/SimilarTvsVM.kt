package com.kirchhoff.movies.ui.screens.similar.tv

import com.kirchhoff.movies.data.ui.main.UIDiscoverTvs
import com.kirchhoff.movies.repository.tv.ITvRepository
import com.kirchhoff.movies.ui.screens.core.PaginatedScreenVM

class SimilarTvsVM(private val tvRepository: ITvRepository) : PaginatedScreenVM<UIDiscoverTvs>() {
    override suspend fun loadData(page: Int, dataId: Int) = tvRepository.fetchSimilarTvs(dataId, page)
}
