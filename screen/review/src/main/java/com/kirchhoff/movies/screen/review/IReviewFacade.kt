package com.kirchhoff.movies.screen.review

import androidx.fragment.app.Fragment

interface IReviewFacade {
    fun movieReview(id: Int): Fragment
    fun tvShowReview(id: Int): Fragment
}
