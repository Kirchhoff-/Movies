package com.kirchhoff.movies.networkdata.core

import com.google.gson.annotations.SerializedName

data class NetworkObjectWithName(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)
