package com.kirchhoff.movies.mapper.review

import com.kirchhoff.movies.data.ui.core.UIPaginated
import com.kirchhoff.movies.data.ui.details.review.UIReview
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.details.review.NetworkReview
import com.kirchhoff.movies.repository.Result

interface IReviewListMapper {
    fun createUIReviewList(reviewsListResponse: Result<NetworkPaginated<NetworkReview>>): Result<UIPaginated<UIReview>>
}
