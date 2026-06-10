package com.kirchhoff.movies.screen.movie.ui.screen.list

import android.os.Parcelable
import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.ui.UIGenre
import com.kirchhoff.movies.screen.movie.data.MovieUICountry
import com.kirchhoff.movies.screen.movie.data.MovieUIProductionCompany
import kotlinx.parcelize.Parcelize

internal sealed interface MovieListType : Parcelable {
    @Parcelize
    data class Genre(val genre: UIGenre) : MovieListType, Parcelable

    @Parcelize
    data class Country(val country: MovieUICountry) : MovieListType, Parcelable

    @Parcelize
    data class Company(val company: MovieUIProductionCompany) : MovieListType, Parcelable

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
