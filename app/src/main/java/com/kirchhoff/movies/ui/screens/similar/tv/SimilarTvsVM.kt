package com.kirchhoff.movies.ui.screens.similar.tv

import com.kirchhoff.movies.data.responses.DiscoverTvsResponse
import com.kirchhoff.movies.repository.tv.ITvRepository
import com.kirchhoff.movies.ui.screens.core.PaginatedScreenVM

class SimilarTvsVM(private val tvRepository: ITvRepository) : PaginatedScreenVM<DiscoverTvsResponse>() {
    override suspend fun loadData(page: Int, dataId: Int) = tvRepository.fetchSimilarTvs(dataId, page)
}
