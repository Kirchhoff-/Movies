package com.kirchhoff.movies.data.network.core

import com.google.gson.annotations.SerializedName

data class NetworkObjectWithName(@SerializedName("name") val name: String)
