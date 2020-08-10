package com.kirchhoff.movies.data.network.details.review

import com.kirchhoff.movies.data.responses.PaginatedResponse

class NetworkReviewsListResponse(
    override val page: Int,
    override val results: List<NetworkReview>,
    override val total_pages: Int,
    override val total_results: Int
) : PaginatedResponse<NetworkReview>

data class NetworkReview(
    val id: String,
    val author: String,
    val content: String
)
