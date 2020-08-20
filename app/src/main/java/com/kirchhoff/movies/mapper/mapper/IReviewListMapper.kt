package com.kirchhoff.movies.mapper.mapper

import com.kirchhoff.movies.data.network.details.review.NetworkReviewsListResponse
import com.kirchhoff.movies.data.ui.core.UIPaginated
import com.kirchhoff.movies.data.ui.details.review.UIReview
import com.kirchhoff.movies.repository.Result

interface IReviewListMapper {
    fun createUIReviewList(reviewsListResponse: Result<NetworkReviewsListResponse>): Result<UIPaginated<UIReview>>
}
