package com.kirchhoff.movies.ui.screens.similar.tv

import com.kirchhoff.movies.data.ui.core.PaginatedData
import com.kirchhoff.movies.data.ui.main.UITv
import com.kirchhoff.movies.repository.tv.ITvRepository
import com.kirchhoff.movies.ui.screens.core.PaginatedScreenVM

class SimilarTvsVM(private val tvRepository: ITvRepository) : PaginatedScreenVM<PaginatedData<UITv>>() {
    override suspend fun loadData(page: Int, dataId: Int) = tvRepository.fetchSimilarTvs(dataId, page)
}
