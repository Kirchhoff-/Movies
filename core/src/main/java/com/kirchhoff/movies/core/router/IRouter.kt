package com.kirchhoff.movies.core.router

import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.data.UITv

interface IRouter {
    fun openMovieDetailsScreen(movie: UIMovie)
    fun openTvDetailsScreen(tv: UITv)
    fun openPersonDetailsScreen(person: UIPerson)
    fun openSimilarTvShowsScreen(tv: UITv)
    fun openReviewsListScreen(movie: UIMovie)
    fun openReviewsListScreen(tv: UITv)
    fun openCastCreditsScreen()
    fun openCrewCreditsScreen()
}
