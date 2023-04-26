package com.kirchhoff.movies.router

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kirchhoff.movies.R
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.router.IRouter
import com.kirchhoff.movies.screen.review.ui.screen.list.ReviewsListFragment
import com.kirchhoff.movies.ui.screens.details.movie.MovieDetailsFragment
import com.kirchhoff.movies.ui.screens.details.person.ui.screen.details.PersonDetailsFragment
import com.kirchhoff.movies.ui.screens.details.person.ui.screen.images.PersonImagesFragment
import com.kirchhoff.movies.ui.screens.details.tv.TvDetailsFragment
import com.kirchhoff.movies.ui.screens.main.MainFragment
import com.kirchhoff.movies.ui.screens.similar.movie.SimilarMoviesFragment
import com.kirchhoff.movies.ui.screens.similar.tv.SimilarTvsFragment

class Router(private val activity: AppCompatActivity) : IRouter {
    override fun openDiscoverScreen() {
        replaceFragment(MainFragment.newInstance(), false)
    }

    override fun openMovieDetailsScreen(movie: UIMovie) {
        replaceFragment(MovieDetailsFragment.newInstance(movie))
    }

    override fun openTvDetailsScreen(tv: UITv) {
        replaceFragment(TvDetailsFragment.newInstance(tv))
    }

    override fun openPersonDetailsScreen(person: UIPerson) {
        replaceFragment(PersonDetailsFragment.newInstance(person))
    }

    override fun openPersonImagesScreen(imagesUrls: List<String>, currentPosition: Int) {
        replaceFragment(PersonImagesFragment.newInstance(imagesUrls, currentPosition))
    }

    override fun openSimilarMoviesScreen(movie: UIMovie) {
        replaceFragment(SimilarMoviesFragment.newInstance(movie.id, movie.title))
    }

    override fun openSimilarTvShowsScreen(tv: UITv) {
        replaceFragment(SimilarTvsFragment.newInstance(tv.id, tv.name))
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
