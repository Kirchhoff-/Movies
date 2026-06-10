package com.kirchhoff.movies.screen.movie.router

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.ui.UIGenre
import com.kirchhoff.movies.core.extensions.replaceFragment
import com.kirchhoff.movies.screen.movie.data.MovieUICountry
import com.kirchhoff.movies.screen.movie.data.MovieUIProductionCompany
import com.kirchhoff.movies.screen.movie.ui.screen.image.MovieImageFragment
import com.kirchhoff.movies.screen.movie.ui.screen.images.MovieImagesFragment
import com.kirchhoff.movies.screen.movie.ui.screen.list.MovieListFragment

internal class MovieRouter(private val activity: AppCompatActivity) {

    fun openMoviesByGenreScreen(genre: UIGenre) {
        activity.replaceFragment(MovieListFragment.byGenre(genre))
    }

    fun openMoviesByCountryScreen(country: MovieUICountry) {
        activity.replaceFragment(MovieListFragment.byCountry(country))
    }

    fun openSimilarMoviesScreen(movieId: MovieId) {
        activity.replaceFragment(MovieListFragment.similarWith(movieId))
    }

    fun openCompanyMoviesScreen(company: MovieUIProductionCompany) {
        activity.replaceFragment(MovieListFragment.byCompany(company))
    }

    fun openImagesScreen(movieId: MovieId) {
        activity.replaceFragment(MovieImagesFragment.newInstance(movieId))
    }

    fun openImage(imagePath: String) {
        activity.replaceFragment(MovieImageFragment.newInstance(imagePath))
    }

    fun openNowPlayingScreen() {
        activity.replaceFragment(MovieListFragment.nowPlaying())
    }

    fun openUpcomingScreen() {
        activity.replaceFragment(MovieListFragment.upcoming())
    }

    fun openPopularScreen() {
        activity.replaceFragment(MovieListFragment.popular())
    }

    fun openTopRatedScreen() {
        activity.replaceFragment(MovieListFragment.topRated())
    }
}
