package com.kirchhoff.movies.router

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.ui.UITv
import com.kirchhoff.movies.core.extensions.replaceFragment
import com.kirchhoff.movies.core.router.IRouter
import com.kirchhoff.movies.screen.credits.CreditsFacade
import com.kirchhoff.movies.screen.movie.MovieFacade
import com.kirchhoff.movies.screen.person.PersonFacade
import com.kirchhoff.movies.screen.review.ReviewFacade
import com.kirchhoff.movies.screen.tvshow.TvShowFacade

class Router(
    private val activity: AppCompatActivity,
    private val movieFacade: MovieFacade,
    private val tvShowFacade: TvShowFacade,
    private val personFacade: PersonFacade,
    private val reviewFacade: ReviewFacade,
    private val creditsFacade: CreditsFacade
) : IRouter {

    override fun openMovieDetailsScreen(movieId: MovieId) {
        activity.replaceFragment(movieFacade.movieDetails(movieId))
    }

    override fun openTvDetailsScreen(tv: UITv) {
        activity.replaceFragment(tvShowFacade.tvShowDetails(tv))
    }

    override fun openPersonDetailsScreen(personId: Int) {
        activity.replaceFragment(personFacade.personDetails(personId))
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
