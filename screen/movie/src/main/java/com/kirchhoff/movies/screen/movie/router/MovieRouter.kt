package com.kirchhoff.movies.screen.movie.router

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.core.data.UIGenre
import com.kirchhoff.movies.screen.movie.R
import com.kirchhoff.movies.screen.movie.ui.screen.genre.MovieListByGenreFragment

class MovieRouter(private val activity: AppCompatActivity) : IMovieRouter {
    override fun openMoviesByGenreScreen(genre: UIGenre) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, MovieListByGenreFragment.newInstance(genre))
            .addToBackStack(null)
            .commit()
    }
}
