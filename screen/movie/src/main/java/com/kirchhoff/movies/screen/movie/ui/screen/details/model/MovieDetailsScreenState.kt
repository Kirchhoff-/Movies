package com.kirchhoff.movies.screen.movie.ui.screen.details.model

import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UIImage
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.movie.data.UIMovieInfo
import com.kirchhoff.movies.screen.movie.data.UITrailersList

internal data class MovieDetailsScreenState(
    val title: StringValue,
    val backdropPath: String?,
    val posterPath: String?,
    val info: UIMovieInfo,
    val trailers: UITrailersList,
    val credits: UIEntertainmentCredits,
    val similarMovies: List<UIMovie>,
    val images: List<UIImage>,
    val isLoading: Boolean,
    val errorMessage: StringValue
)
