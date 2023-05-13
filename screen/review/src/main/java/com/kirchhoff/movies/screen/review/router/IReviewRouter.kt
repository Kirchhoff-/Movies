package com.kirchhoff.movies.screen.review.router

import com.kirchhoff.movies.screen.review.data.UIReview

internal interface IReviewRouter {
    fun openDetailsScreen(review: UIReview, title: String)
}
