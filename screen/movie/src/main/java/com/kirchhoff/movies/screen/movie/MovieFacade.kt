package com.kirchhoff.movies.screen.movie

import androidx.fragment.app.Fragment
import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.screen.movie.ui.screen.details.MovieDetailsFragment
import com.kirchhoff.movies.screen.movie.ui.screen.discover.MovieDiscoverFragment

class MovieFacade {
    fun movieList(): Fragment = MovieDiscoverFragment.newInstance()

    fun movieDetails(movieId: MovieId): Fragment = MovieDetailsFragment.newInstance(movieId)
}
