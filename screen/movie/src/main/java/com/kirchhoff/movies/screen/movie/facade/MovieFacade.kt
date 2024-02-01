package com.kirchhoff.movies.screen.movie.facade

import androidx.fragment.app.Fragment
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.screen.movie.IMovieFacade
import com.kirchhoff.movies.screen.movie.ui.screen.details.OldMovieDetailsFragment
import com.kirchhoff.movies.screen.movie.ui.screen.list.MovieListFragment

class MovieFacade : IMovieFacade {

    override fun movieList(): Fragment = MovieListFragment.discover()

    override fun movieDetails(movie: UIMovie): Fragment = OldMovieDetailsFragment.newInstance(movie)
}
