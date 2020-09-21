package com.kirchhoff.movies.data.network.details.tv

import com.google.gson.annotations.SerializedName
import com.kirchhoff.movies.data.network.core.NetworkObjectWithName

class NetworkTvDetails(
    @SerializedName("number_of_seasons") val numberOfSeasons: Int,
    @SerializedName("number_of_episodes") val numberOfEpisodes: Int,
    @SerializedName("overview") val overview: String,
    @SerializedName("status") val status: String,
    @SerializedName("first_air_date") val firstAirDate: String,
    @SerializedName("vote_count") val voteCount: Int?,
    @SerializedName("vote_average") val voteAverage: Float?,
    @SerializedName("genres") val genres: List<NetworkObjectWithName>
)
