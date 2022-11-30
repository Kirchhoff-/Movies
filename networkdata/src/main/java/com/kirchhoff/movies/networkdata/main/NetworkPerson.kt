package com.kirchhoff.movies.networkdata.main

import com.google.gson.annotations.SerializedName

data class NetworkPerson(
    val id: Int,
    val name: String,
    @SerializedName("profile_path") val profilePath: String?
)
