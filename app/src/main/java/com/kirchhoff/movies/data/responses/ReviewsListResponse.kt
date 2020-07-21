package com.kirchhoff.movies.data.responses

import com.kirchhoff.movies.data.Review

class ReviewsListResponse(
    override val page: Int,
    override val results: List<Review>,
    override val total_pages: Int,
    override val total_results: Int
) : PaginatedResponse<Review>