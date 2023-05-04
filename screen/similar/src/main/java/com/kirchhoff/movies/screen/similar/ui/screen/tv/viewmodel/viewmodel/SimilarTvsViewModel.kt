package com.kirchhoff.movies.screen.similar.ui.screen.tv.viewmodel.viewmodel

import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenVM
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.screen.similar.repository.ISimilarRepository

class SimilarTvsViewModel(
    private val tvId: Int,
    private val similarRepository: ISimilarRepository
) : PaginatedScreenVM<UIPaginated<UITv>>() {
    override suspend fun loadData(page: Int) = similarRepository.fetchTvs(tvId, page)
}
