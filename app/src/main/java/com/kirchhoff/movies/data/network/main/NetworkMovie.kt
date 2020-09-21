package com.kirchhoff.movies.data.network.main

import com.google.gson.annotations.SerializedName

data class NetworkMovie(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?
)
