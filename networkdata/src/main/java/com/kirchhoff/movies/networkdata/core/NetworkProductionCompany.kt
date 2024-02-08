package com.kirchhoff.movies.networkdata.core

import com.google.gson.annotations.SerializedName

data class NetworkProductionCompany(
    @SerializedName("id") val id: String,
    @SerializedName("logo_path") val logoPath: String?,
    @SerializedName("name") val name: String
)
