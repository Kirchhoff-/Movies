package com.kirchhoff.movies.networkdata.details.movie

import com.google.gson.annotations.SerializedName
import com.kirchhoff.movies.networkdata.core.NetworkObjectWithName
import com.kirchhoff.movies.networkdata.core.NetworkProductionCompany

data class NetworkMovieDetails(
    @SerializedName("production_countries") val productionCountries: List<NetworkCountry>,
    @SerializedName("production_companies") val productionCompanies: List<NetworkProductionCompany>,
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

data class NetworkCountry(
    @SerializedName("iso_3166_1") val id: String,
    @SerializedName("name") val name: String
)
