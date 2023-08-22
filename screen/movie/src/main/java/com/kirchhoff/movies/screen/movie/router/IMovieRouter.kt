package com.kirchhoff.movies.screen.movie.router

import com.kirchhoff.movies.core.data.UIGenre
import com.kirchhoff.movies.core.data.UIMovie

interface IMovieRouter {
    fun openMoviesByGenreScreen(genre: UIGenre)
    fun openMoviesByCountryScreen(countryId: String, countryName: String)
    fun openSimilarMoviesScreen(movie: UIMovie)
}
