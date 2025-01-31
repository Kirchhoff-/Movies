package com.kirchhoff.movies.router

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.ui.UIPerson
import com.kirchhoff.movies.core.data.ui.UITv
import com.kirchhoff.movies.core.extensions.replaceFragment
import com.kirchhoff.movies.core.router.IRouter
import com.kirchhoff.movies.screen.credits.ICreditsFacade
import com.kirchhoff.movies.screen.movie.IMovieFacade
import com.kirchhoff.movies.screen.person.IPersonFacade
import com.kirchhoff.movies.screen.review.IReviewFacade
import com.kirchhoff.movies.screen.tvshow.ITvShowFacade

class Router(
    private val activity: AppCompatActivity,
    private val movieFacade: IMovieFacade,
    private val tvShowFacade: ITvShowFacade,
    private val personFacade: IPersonFacade,
    private val reviewFacade: IReviewFacade,
    private val creditsFacade: ICreditsFacade
) : IRouter {

    override fun openMovieDetailsScreen(movieId: MovieId) {
        activity.replaceFragment(movieFacade.movieDetails(movieId))
    }

    override fun openTvDetailsScreen(tv: UITv) {
        activity.replaceFragment(tvShowFacade.tvShowDetails(tv))
    }

    override fun openPersonDetailsScreen(person: UIPerson) {
        activity.replaceFragment(personFacade.personDetails(person))
    }

    override fun openReviewsListScreen(movieId: MovieId) {
        activity.replaceFragment(reviewFacade.movieReview(movieId))
    }

    override fun openReviewsListScreen(tv: UITv) {
        activity.replaceFragment(reviewFacade.tvShowReview(tv.id))
    }

    override fun openCastCreditsScreen(movieId: MovieId) {
        activity.replaceFragment(creditsFacade.castCredits(movieId))
    }

    override fun openCrewCreditsScreen(movieId: MovieId) {
        activity.replaceFragment(creditsFacade.crewCredits(movieId))
    }
}
