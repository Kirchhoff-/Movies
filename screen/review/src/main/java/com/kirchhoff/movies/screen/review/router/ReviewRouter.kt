package com.kirchhoff.movies.screen.review.router

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.core.extensions.replaceFragment
import com.kirchhoff.movies.screen.review.data.UIReview
import com.kirchhoff.movies.screen.review.ui.screen.details.ReviewDetailsFragment

internal interface IReviewRouter {
    fun openDetailsScreen(review: UIReview, title: String)
}

internal class ReviewRouter(private val activity: AppCompatActivity) : IReviewRouter {
    override fun openDetailsScreen(review: UIReview, title: String) {
        activity.replaceFragment(ReviewDetailsFragment.newInstance(review, title))
    }
}
