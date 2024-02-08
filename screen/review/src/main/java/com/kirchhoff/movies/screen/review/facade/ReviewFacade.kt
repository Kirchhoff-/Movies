package com.kirchhoff.movies.screen.review.facade

import androidx.fragment.app.Fragment
import com.kirchhoff.movies.screen.review.IReviewFacade
import com.kirchhoff.movies.screen.review.ui.screen.list.ReviewsListFragment

class ReviewFacade : IReviewFacade {
    override fun movieReview(id: Int): Fragment = ReviewsListFragment.newInstanceForMovie(id)

    override fun tvShowReview(id: Int): Fragment = ReviewsListFragment.newInstanceForTvShow(id)
}
