package com.kirchhoff.movies.networkdata.details.person

import com.google.gson.annotations.SerializedName

data class NetworkPersonImages(@SerializedName("profiles") val images: List<NetworkPersonImage>)
