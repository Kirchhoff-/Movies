package com.kirchhoff.movies.core.router

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.ui.UITv

interface IRouter {
    fun openMovieDetailsScreen(movieId: MovieId)
    fun openTvDetailsScreen(tv: UITv)
    fun openPersonDetailsScreen(personId: Int)
    fun openReviewsListScreen(movieId: MovieId)
    fun openReviewsListScreen(tv: UITv)
    fun openCastCreditsScreen(movieId: MovieId)
    fun openCrewCreditsScreen(movieId: MovieId)
}
