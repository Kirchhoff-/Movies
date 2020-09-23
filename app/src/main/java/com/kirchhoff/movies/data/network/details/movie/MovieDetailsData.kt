package com.kirchhoff.movies.data.network.details.movie

import com.google.gson.annotations.SerializedName
import com.kirchhoff.movies.data.network.core.NetworkObjectWithName

data class NetworkMovieDetails(
    @SerializedName("production_countries") val productionCountries: List<NetworkObjectWithName>,
    @SerializedName("runtime")val runtime: Int?,
    @SerializedName("tagline") val tagline: String?,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("vote_count") val voteCount: Int?,
    @SerializedName("vote_average") val voteAverage: Float?,
    @SerializedName("genres") val genres: List<NetworkObjectWithName>
)

data class NetworkTrailer(
    val site: String,
    val key: String
)

data class NetworkTrailersList(val results: List<NetworkTrailer>)
