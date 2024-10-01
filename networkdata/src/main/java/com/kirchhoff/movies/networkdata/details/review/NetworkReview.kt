package com.kirchhoff.movies.networkdata.details.review

import com.google.gson.annotations.SerializedName

data class NetworkReview(
    @SerializedName("author") val author: String,
    @SerializedName("content") val content: String,
    @SerializedName("author_details") val authorDetails: NetworkReviewAuthorDetails,
    @SerializedName("url") val url: String
)
