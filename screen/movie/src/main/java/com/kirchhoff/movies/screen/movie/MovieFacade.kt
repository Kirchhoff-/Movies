package com.kirchhoff.movies.screen.movie

import androidx.fragment.app.Fragment
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.screen.movie.ui.screen.details.MovieDetailsFragment
import com.kirchhoff.movies.screen.movie.ui.screen.discover.MovieDiscoverFragment

interface IMovieFacade {
    fun movieList(): Fragment
    fun movieDetails(movie: UIMovie): Fragment
}

class MovieFacade : IMovieFacade {
    override fun movieList(): Fragment = MovieDiscoverFragment.newInstance()

    override fun movieDetails(movie: UIMovie): Fragment = MovieDetailsFragment.newInstance(movie)
}
