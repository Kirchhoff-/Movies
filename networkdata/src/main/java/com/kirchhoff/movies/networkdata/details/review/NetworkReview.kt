package com.kirchhoff.movies.networkdata.details.review

import com.google.gson.annotations.SerializedName

data class NetworkReview(
    @SerializedName("author") val author: String,
    @SerializedName("content") val content: String,
    @SerializedName("author_details") val authorDetails: AuthorDetails,
    @SerializedName("url") val url: String
) {
    data class AuthorDetails(
        @SerializedName("avatar_path") val avatar: String?,
        @SerializedName("rating") val rating: Int?
    )
}
