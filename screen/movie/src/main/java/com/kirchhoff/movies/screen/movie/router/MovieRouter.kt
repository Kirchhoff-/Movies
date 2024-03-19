package com.kirchhoff.movies.screen.movie.router

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kirchhoff.movies.core.R
import com.kirchhoff.movies.core.data.UIGenre
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.screen.movie.data.UICountry
import com.kirchhoff.movies.screen.movie.data.UIProductionCompany
import com.kirchhoff.movies.screen.movie.ui.screen.image.MovieImageFragment
import com.kirchhoff.movies.screen.movie.ui.screen.images.MovieImagesFragment
import com.kirchhoff.movies.screen.movie.ui.screen.list.MovieListFragment

internal interface IMovieRouter {
    fun openMoviesByGenreScreen(genre: UIGenre)
    fun openMoviesByCountryScreen(country: UICountry)
    fun openCompanyMoviesScreen(company: UIProductionCompany)
    fun openSimilarMoviesScreen(movie: UIMovie)
    fun openImagesScreen(movie: UIMovie)
    fun openImage(imagePath: String)
}

internal class MovieRouter(private val activity: AppCompatActivity) : IMovieRouter {

    override fun openMoviesByGenreScreen(genre: UIGenre) {
        replaceFragment(MovieListFragment.byGenre(genre))
    }

    override fun openMoviesByCountryScreen(country: UICountry) {
        replaceFragment(MovieListFragment.byCountry(country))
    }

    override fun openSimilarMoviesScreen(movie: UIMovie) {
        replaceFragment(MovieListFragment.similarWith(movie))
    }

    override fun openCompanyMoviesScreen(company: UIProductionCompany) {
        replaceFragment(MovieListFragment.byCompany(company))
    }

    override fun openImagesScreen(movie: UIMovie) {
        replaceFragment(MovieImagesFragment.newInstance(movie))
    }

    override fun openImage(imagePath: String) {
        replaceFragment(MovieImageFragment.newInstance(imagePath))
    }

    private fun replaceFragment(fragment: Fragment) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}
