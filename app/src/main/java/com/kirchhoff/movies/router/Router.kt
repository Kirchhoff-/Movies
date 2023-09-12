package com.kirchhoff.movies.router

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kirchhoff.movies.R
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.router.IRouter
import com.kirchhoff.movies.screen.movie.ui.screen.details.MovieDetailsFragment
import com.kirchhoff.movies.screen.person.ui.screen.details.PersonDetailsFragment
import com.kirchhoff.movies.screen.review.ui.screen.list.ReviewsListFragment
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.TvShowDetailsFragment
import com.kirchhoff.movies.screen.tvshow.ui.screen.similar.TvShowSimilarFragment
import com.kirchhoff.movies.ui.screens.main.MainFragment

class Router(private val activity: AppCompatActivity) : IRouter {
    override fun openDiscoverScreen() {
        replaceFragment(MainFragment.newInstance(), false)
    }

    override fun openMovieDetailsScreen(movie: UIMovie) {
        replaceFragment(MovieDetailsFragment.newInstance(movie))
    }

    override fun openTvDetailsScreen(tv: UITv) {
        replaceFragment(TvShowDetailsFragment.newInstance(tv))
    }

    override fun openPersonDetailsScreen(person: UIPerson) {
        replaceFragment(PersonDetailsFragment.newInstance(person))
    }

    override fun openSimilarTvShowsScreen(tv: UITv) {
        replaceFragment(TvShowSimilarFragment.newInstance(tv.id, tv.name))
    }

    override fun openReviewsListScreen(movie: UIMovie) {
        replaceFragment(ReviewsListFragment.newInstanceForMovie(movie.id, movie.title))
    }

    override fun openReviewsListScreen(tv: UITv) {
        replaceFragment(ReviewsListFragment.newInstanceForTvShow(tv.id, tv.name))
    }

    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)

        if (addToBackStack) transaction.addToBackStack(null)

        transaction.commit()
    }
}
