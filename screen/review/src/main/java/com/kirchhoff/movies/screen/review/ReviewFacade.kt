package com.kirchhoff.movies.screen.review

import androidx.fragment.app.Fragment
import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.TvId
import com.kirchhoff.movies.screen.review.ui.screen.list.ReviewsListFragment

interface IReviewFacade {
    fun movieReview(id: MovieId): Fragment
    fun tvShowReview(id: TvId): Fragment
}

class ReviewFacade : IReviewFacade {
    override fun movieReview(id: MovieId): Fragment = ReviewsListFragment.newInstanceForMovie(id)

    override fun tvShowReview(id: TvId): Fragment = ReviewsListFragment.newInstanceForTvShow(id)
}
