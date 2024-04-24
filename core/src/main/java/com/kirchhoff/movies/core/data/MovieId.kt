package com.kirchhoff.movies.core.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@JvmInline
value class MovieId(val value: Int) : Parcelable
