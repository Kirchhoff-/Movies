package com.kirchhoff.movies.networkdata.details.review

import com.google.gson.annotations.SerializedName

data class NetworkReviewAuthorDetails(
    @SerializedName("avatar_path") val avatar: String?,
    val rating: Int?
)
