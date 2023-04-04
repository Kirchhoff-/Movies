package com.kirchhoff.movies.core.extensions

import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation
import com.kirchhoff.movies.core.R

const val BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w342"
private const val YOUTUBE_POSTER_PATH = "https://img.youtube.com/vi/%s/0.jpg"

fun ImageView.downloadPoster(path: String?) {
    path?.let { load(BASE_POSTER_PATH + path) }
}

fun ImageView.downloadAvatar(path: String?) {
    path?.let {
        load(BASE_POSTER_PATH + path) {
            placeholder(R.drawable.ic_account_circle)
            error(R.drawable.ic_account_circle)
            transformations(CircleCropTransformation())
        }
    }
}

fun ImageView.downloadYoutubePoster(path: String?) {
    path?.let { load(String.format(YOUTUBE_POSTER_PATH, path)) }
}
