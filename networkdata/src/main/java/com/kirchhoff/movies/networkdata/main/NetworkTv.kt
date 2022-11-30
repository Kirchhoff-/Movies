package com.kirchhoff.movies.networkdata.main

import com.google.gson.annotations.SerializedName

data class NetworkTv(
    @SerializedName("id") val id: Int,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("name") val name: String
)
