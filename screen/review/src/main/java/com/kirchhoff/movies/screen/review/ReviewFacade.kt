package com.kirchhoff.movies.screen.review

import androidx.fragment.app.Fragment
import com.kirchhoff.movies.screen.review.ui.screen.list.ReviewsListFragment

interface IReviewFacade {
    fun movieReview(id: Int): Fragment
    fun tvShowReview(id: Int): Fragment
}

class ReviewFacade : IReviewFacade {
    override fun movieReview(id: Int): Fragment = ReviewsListFragment.newInstanceForMovie(id)

    override fun tvShowReview(id: Int): Fragment = ReviewsListFragment.newInstanceForTvShow(id)
}
