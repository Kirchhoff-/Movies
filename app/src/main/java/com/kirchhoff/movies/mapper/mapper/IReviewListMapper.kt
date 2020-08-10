package com.kirchhoff.movies.mapper.mapper

import com.kirchhoff.movies.data.network.details.review.NetworkReviewsListResponse
import com.kirchhoff.movies.data.ui.details.review.UIReviewsListResponse
import com.kirchhoff.movies.repository.Result

interface IReviewListMapper {
    fun createUIReviewList(reviewsListResponse: Result<NetworkReviewsListResponse>): Result<UIReviewsListResponse>
}
