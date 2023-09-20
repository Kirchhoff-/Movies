package com.kirchhoff.movies.screen.review.router

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.core.R
import com.kirchhoff.movies.screen.review.data.UIReview
import com.kirchhoff.movies.screen.review.ui.screen.details.ReviewDetailsFragment

internal class ReviewRouter(private val activity: AppCompatActivity) : IReviewRouter {
    override fun openDetailsScreen(review: UIReview, title: String) {
        activity.supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, ReviewDetailsFragment.newInstance(review, title))
            .addToBackStack(null)
            .commit()
    }
}
