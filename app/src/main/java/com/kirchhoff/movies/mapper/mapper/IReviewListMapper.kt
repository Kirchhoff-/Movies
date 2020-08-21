package com.kirchhoff.movies.mapper.mapper

import com.kirchhoff.movies.data.network.core.NetworkPaginated
import com.kirchhoff.movies.data.network.details.review.NetworkReview
import com.kirchhoff.movies.data.ui.core.UIPaginated
import com.kirchhoff.movies.data.ui.details.review.UIReview
import com.kirchhoff.movies.repository.Result

interface IReviewListMapper {
    fun createUIReviewList(reviewsListResponse: Result<NetworkPaginated<NetworkReview>>): Result<UIPaginated<UIReview>>
}
