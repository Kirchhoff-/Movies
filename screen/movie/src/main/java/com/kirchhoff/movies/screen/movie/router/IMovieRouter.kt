package com.kirchhoff.movies.screen.movie.router

import com.kirchhoff.movies.core.data.UIGenre
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.screen.movie.data.UIProductionCompany

internal interface IMovieRouter {
    fun openMoviesByGenreScreen(genre: UIGenre)
    fun openMoviesByCountryScreen(countryId: String, countryName: String)
    fun openCompanyMoviesScreen(company: UIProductionCompany)
    fun openSimilarMoviesScreen(movie: UIMovie)
    fun openImagesScreen(movie: UIMovie)
    fun openImage(imagePath: String)
}
