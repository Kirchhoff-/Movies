package com.kirchhoff.movies.screen.review

import androidx.fragment.app.Fragment
import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.TvId
import com.kirchhoff.movies.screen.review.ui.screen.list.ReviewsListFragment

class ReviewFacade {
    fun movieReview(id: MovieId): Fragment = ReviewsListFragment.newInstanceForMovie(id)

    fun tvShowReview(id: TvId): Fragment = ReviewsListFragment.newInstanceForTvShow(id)
}
