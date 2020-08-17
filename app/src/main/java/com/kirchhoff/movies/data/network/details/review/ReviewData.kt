package com.kirchhoff.movies.data.network.details.review

class NetworkReviewsListResponse(
    val page: Int,
    val results: List<NetworkReview>,
    val total_pages: Int
)

data class NetworkReview(
    val id: String,
    val author: String,
    val content: String
)
