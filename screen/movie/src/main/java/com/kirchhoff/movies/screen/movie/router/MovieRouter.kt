package com.kirchhoff.movies.screen.movie.router

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.UIGenre
import com.kirchhoff.movies.core.extensions.replaceFragment
import com.kirchhoff.movies.screen.movie.data.UICountry
import com.kirchhoff.movies.screen.movie.data.UIProductionCompany
import com.kirchhoff.movies.screen.movie.ui.screen.image.MovieImageFragment
import com.kirchhoff.movies.screen.movie.ui.screen.images.MovieImagesFragment
import com.kirchhoff.movies.screen.movie.ui.screen.list.MovieListFragment

internal interface IMovieRouter {
    fun openMoviesByGenreScreen(genre: UIGenre)
    fun openMoviesByCountryScreen(country: UICountry)
    fun openCompanyMoviesScreen(company: UIProductionCompany)
    fun openSimilarMoviesScreen(movieId: MovieId)
    fun openImagesScreen(movieId: MovieId)
    fun openImage(imagePath: String)
    fun openNowPlayingScreen()
    fun openUpcomingScreen()
    fun openPopularScreen()
    fun openTopRatedScreen()
}

internal class MovieRouter(private val activity: AppCompatActivity) : IMovieRouter {

    override fun openMoviesByGenreScreen(genre: UIGenre) {
        activity.replaceFragment(MovieListFragment.byGenre(genre))
    }

    override fun openMoviesByCountryScreen(country: UICountry) {
        activity.replaceFragment(MovieListFragment.byCountry(country))
    }

    override fun openSimilarMoviesScreen(movieId: MovieId) {
        activity.replaceFragment(MovieListFragment.similarWith(movieId))
    }

    override fun openCompanyMoviesScreen(company: UIProductionCompany) {
        activity.replaceFragment(MovieListFragment.byCompany(company))
    }

    override fun openImagesScreen(movieId: MovieId) {
        activity.replaceFragment(MovieImagesFragment.newInstance(movieId))
    }

    override fun openImage(imagePath: String) {
        activity.replaceFragment(MovieImageFragment.newInstance(imagePath))
    }

    override fun openNowPlayingScreen() {
        activity.replaceFragment(MovieListFragment.nowPlaying())
    }

    override fun openUpcomingScreen() {
        activity.replaceFragment(MovieListFragment.upcoming())
    }

    override fun openPopularScreen() {
        activity.replaceFragment(MovieListFragment.popular())
    }

    override fun openTopRatedScreen() {
        activity.replaceFragment(MovieListFragment.topRated())
    }
}
