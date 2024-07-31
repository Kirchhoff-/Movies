package com.kirchhoff.movies.core.data

data class UIImage(
    val path: String,
    val height: Int,
    val width: Int
) {
    companion object {
        val Default = UIImage(
            path = "",
            height = 0,
            width = 0
        )
    }
}
