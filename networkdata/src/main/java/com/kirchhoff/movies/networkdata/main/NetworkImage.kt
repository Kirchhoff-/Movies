package com.kirchhoff.movies.networkdata.main

import com.google.gson.annotations.SerializedName

data class NetworkImage(
    @SerializedName("file_path") val path: String,
    @SerializedName("height") val height: Int,
    @SerializedName("width") val width: Int
)
