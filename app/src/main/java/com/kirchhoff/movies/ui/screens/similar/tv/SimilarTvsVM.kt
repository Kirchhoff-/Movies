package com.kirchhoff.movies.ui.screens.similar.tv

import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenVM
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.repository.tv.ITvRepository

class SimilarTvsVM(
    private val tvId: Int,
    private val tvRepository: ITvRepository
) : PaginatedScreenVM<UIPaginated<UITv>>() {
    override suspend fun loadData(page: Int) = tvRepository.fetchSimilarTvs(tvId, page)
}
