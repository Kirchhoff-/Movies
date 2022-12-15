package com.kirchhoff.movies.core.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

private const val BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w342"
private const val YOUTUBE_POSTER_PATH = "https://img.youtube.com/vi/%s/0.jpg"

fun ImageView.downloadPoster(path: String?) {
    path?.let {
        Glide.with(context)
            .load(BASE_POSTER_PATH + path)
            .into(this)
    }
}

fun ImageView.downloadAvatar(path: String?) {
    path?.let {
        Glide.with(context)
            .load(BASE_POSTER_PATH + path)
            .apply(RequestOptions().centerCrop().circleCrop())
            .into(this)
    }
}

fun ImageView.downloadYoutubePoster(path: String?) {
    path?.let {
        Glide.with(context)
            .load(String.format(YOUTUBE_POSTER_PATH, path))
            .into(this)
    }
}
