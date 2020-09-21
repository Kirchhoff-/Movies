package com.kirchhoff.movies.data.network.core

import com.google.gson.annotations.SerializedName

data class NetworkPaginated<T>(
    val page: Int,
    val results: List<T>,
    @SerializedName("total_pages") val totalPages: Int
)
