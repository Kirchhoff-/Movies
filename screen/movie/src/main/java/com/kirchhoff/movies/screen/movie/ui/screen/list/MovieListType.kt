package com.kirchhoff.movies.screen.movie.ui.screen.list

import android.os.Parcelable
import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.ui.UIGenre
import com.kirchhoff.movies.screen.movie.data.UICountry
import com.kirchhoff.movies.screen.movie.data.UIProductionCompany
import kotlinx.parcelize.Parcelize

internal sealed interface MovieListType : Parcelable {
    @Parcelize
    data class Genre(val genre: UIGenre) : MovieListType, Parcelable

    @Parcelize
    data class Country(val country: UICountry) : MovieListType, Parcelable

    @Parcelize
    data class Company(val company: UIProductionCompany) : MovieListType, Parcelable

    @Parcelize
    data class Similar(val movieId: MovieId) : MovieListType, Parcelable

    @Parcelize
    object NowPlaying : MovieListType, Parcelable

    @Parcelize
    object Upcoming : MovieListType, Parcelable

    @Parcelize
    object Popular : MovieListType, Parcelable

    @Parcelize
    object TopRated : MovieListType, Parcelable
}
