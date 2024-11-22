package com.kirchhoff.movies.core.router

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.data.UITv

interface IRouter {
    fun openMovieDetailsScreen(movieId: MovieId)
    fun openTvDetailsScreen(tv: UITv)
    fun openPersonDetailsScreen(person: UIPerson)
    fun openReviewsListScreen(movieId: MovieId)
    fun openReviewsListScreen(tv: UITv)
    fun openCastCreditsScreen(movieId: MovieId)
    fun openCrewCreditsScreen(movieId: MovieId)
}
