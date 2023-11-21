package com.kirchhoff.movies.router

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kirchhoff.movies.R
import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.data.UITv
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

    override fun openMovieDetailsScreen(movie: UIMovie) {
        replaceFragment(movieFacade.movieDetails(movie))
    }

    override fun openTvDetailsScreen(tv: UITv) {
        replaceFragment(tvShowFacade.tvShowDetails(tv))
    }

    override fun openPersonDetailsScreen(person: UIPerson) {
        replaceFragment(personFacade.personDetails(person))
    }

    override fun openSimilarTvShowsScreen(tv: UITv) {
        replaceFragment(tvShowFacade.similarTvShow(tv))
    }

    override fun openReviewsListScreen(movie: UIMovie) {
        replaceFragment(reviewFacade.movieReview(movie.id, movie.title))
    }

    override fun openReviewsListScreen(tv: UITv) {
        replaceFragment(reviewFacade.tvShowReview(tv.id, tv.name))
    }

    override fun openCastCreditsScreen(actors: List<UIEntertainmentPerson.Actor>) {
        replaceFragment(creditsFacade.castCredits(actors))
    }

    override fun openCrewCreditsScreen(creators: List<UIEntertainmentPerson.Creator>) {
        replaceFragment(creditsFacade.crewCredits(creators))
    }

    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)

        if (addToBackStack) transaction.addToBackStack(null)

        transaction.commit()
    }
}
