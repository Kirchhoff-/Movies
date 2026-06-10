package com.kirchhoff.movies.screen.movie.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class MovieUICountry(val id: String, val name: String) : Parcelable
