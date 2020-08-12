package com.kirchhoff.movies.data.ui.main

import com.kirchhoff.movies.data.Tv
import com.kirchhoff.movies.data.responses.PaginatedResponse

data class UIDiscoverTvs(
    override val page: Int,
    override val results: List<Tv>,
    override val total_results: Int,
    override val total_pages: Int
) : PaginatedResponse<Tv>
