package com.kirchhoff.movies.networkdata.core

import com.google.gson.annotations.SerializedName
import com.kirchhoff.movies.networkdata.main.NetworkImage

data class NetworkImagesResponse(
    @SerializedName("backdrops") val backdrops: List<NetworkImage>,
    @SerializedName("logoc") val logos: List<NetworkImage>?,
    @SerializedName("posters") val posters: List<NetworkImage>
) {
    fun combinedImages(): List<NetworkImage> = backdrops + logos.orEmpty() + posters
}
