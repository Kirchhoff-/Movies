package com.kirchhoff.movies.screen.movie.router

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.core.data.UIGenre
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.screen.movie.R
import com.kirchhoff.movies.screen.movie.ui.screen.country.MovieCountryFragment
import com.kirchhoff.movies.screen.movie.ui.screen.genre.MovieListByGenreFragment
import com.kirchhoff.movies.screen.movie.ui.screen.similar.MovieSimilarFragment

class MovieRouter(private val activity: AppCompatActivity) : IMovieRouter {

    override fun openMoviesByGenreScreen(genre: UIGenre) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, MovieListByGenreFragment.newInstance(genre))
            .addToBackStack(null)
            .commit()
    }

    override fun openMoviesByCountryScreen(countryId: String, countryName: String) {
        activity.supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                MovieCountryFragment.newInstance(countryId, countryName)
            )
            .addToBackStack(null)
            .commit()
    }

    override fun openSimilarMoviesScreen(movie: UIMovie) {
        activity.supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                MovieSimilarFragment.newInstance(movie.id, movie.title)
            )
            .addToBackStack(null)
            .commit()
    }
}
