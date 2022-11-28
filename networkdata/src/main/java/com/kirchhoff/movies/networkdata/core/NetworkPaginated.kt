package com.kirchhoff.movies.networkdata.core

import com.google.gson.annotations.SerializedName

data class NetworkPaginated<T>(
    val page: Int,
    val results: List<T>,
    @SerializedName("total_pages") val totalPages: Int
)
