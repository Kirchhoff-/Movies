package com.kirchhoff.movies.screen.movie.router

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.core.R
import com.kirchhoff.movies.core.data.UIGenre
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.screen.movie.data.UIProductionCompany
import com.kirchhoff.movies.screen.movie.ui.screen.image.MovieImageFragment
import com.kirchhoff.movies.screen.movie.ui.screen.images.MovieImagesFragment
import com.kirchhoff.movies.screen.movie.ui.screen.list.MovieListFragment

internal interface IMovieRouter {
    fun openMoviesByGenreScreen(genre: UIGenre)
    fun openMoviesByCountryScreen(countryId: String, countryName: String)
    fun openCompanyMoviesScreen(company: UIProductionCompany)
    fun openSimilarMoviesScreen(movie: UIMovie)
    fun openImagesScreen(movie: UIMovie)
    fun openImage(imagePath: String)
}

internal class MovieRouter(private val activity: AppCompatActivity) : IMovieRouter {

    override fun openMoviesByGenreScreen(genre: UIGenre) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, MovieListFragment.byGenre(genre))
            .addToBackStack(null)
            .commit()
    }

    override fun openMoviesByCountryScreen(countryId: String, countryName: String) {
        activity.supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                MovieListFragment.byCountry(countryId, countryName)
            )
            .addToBackStack(null)
            .commit()
    }

    override fun openSimilarMoviesScreen(movie: UIMovie) {
        activity.supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                MovieListFragment.similarWith(movie)
            )
            .addToBackStack(null)
            .commit()
    }

    override fun openCompanyMoviesScreen(company: UIProductionCompany) {
        activity.supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                MovieListFragment.byCompany(company)
            )
            .addToBackStack(null)
            .commit()
    }

    override fun openImagesScreen(movie: UIMovie) {
        activity.supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                MovieImagesFragment.newInstance(movie)
            )
            .addToBackStack(null)
            .commit()
    }

    override fun openImage(imagePath: String) {
        activity.supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                MovieImageFragment.newInstance(imagePath)
            )
            .addToBackStack(null)
            .commit()
    }
}
