package com.kirchhoff.movies.screen.review

import androidx.fragment.app.Fragment

interface IReviewFacade {
    fun movieReview(id: Int, title: String?): Fragment
    fun tvShowReview(id: Int, title: String?): Fragment
}
