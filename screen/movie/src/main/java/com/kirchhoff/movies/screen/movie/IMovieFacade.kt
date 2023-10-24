package com.kirchhoff.movies.screen.movie

import androidx.fragment.app.Fragment
import com.kirchhoff.movies.core.data.UIMovie

interface IMovieFacade {
    fun movieList(): Fragment
    fun movieDetails(movie: UIMovie): Fragment
}
