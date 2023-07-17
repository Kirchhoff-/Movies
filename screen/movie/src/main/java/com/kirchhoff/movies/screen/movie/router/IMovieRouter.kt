package com.kirchhoff.movies.screen.movie.router

import com.kirchhoff.movies.core.data.UIGenre

interface IMovieRouter {
    fun openMoviesByGenreScreen(genre: UIGenre)
}
