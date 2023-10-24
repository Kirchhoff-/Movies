package com.kirchhoff.movies.screen.movie.facade

import androidx.fragment.app.Fragment
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.screen.movie.IMovieFacade
import com.kirchhoff.movies.screen.movie.ui.screen.details.MovieDetailsFragment
import com.kirchhoff.movies.screen.movie.ui.screen.discover.MovieListDiscoverFragment

class MovieFacade : IMovieFacade {

    override fun movieList(): Fragment = MovieListDiscoverFragment()

    override fun movieDetails(movie: UIMovie): Fragment = MovieDetailsFragment.newInstance(movie)
}
