package com.kirchhoff.movies.networkdata.main

import com.google.gson.annotations.SerializedName

data class NetworkPerson(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("profile_path") val profilePath: String?
)
