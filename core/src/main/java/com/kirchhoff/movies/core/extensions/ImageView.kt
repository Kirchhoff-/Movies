package com.kirchhoff.movies.core.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.imageLoader
import coil.load
import coil.request.ImageRequest

const val BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w342"
private const val YOUTUBE_POSTER_PATH = "https://img.youtube.com/vi/%s/0.jpg"

fun ImageView.downloadPoster(path: String?) {
    path?.let { load(BASE_POSTER_PATH + path) }
}

fun ImageView.downloadAvatar(
    path: String?,
    @DrawableRes placeholderImageResources: Int
) {
    if (path != null) {
        val request = ImageRequest.Builder(context)
            .data(BASE_POSTER_PATH + path)
            .target(
                onStart = { setEmptyAvatar(placeholderImageResources) },
                onSuccess = { result ->
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    this.setImageDrawable(result)
                }
            )
            .build()

        context.imageLoader.enqueue(request)
    } else {
        setEmptyAvatar(placeholderImageResources)
    }
}

fun ImageView.downloadYoutubePoster(path: String?) {
    path?.let { load(String.format(YOUTUBE_POSTER_PATH, path)) }
}

private fun ImageView.setEmptyAvatar(@DrawableRes placeholderImageResources: Int) {
    scaleType = ImageView.ScaleType.CENTER
    setImageResource(placeholderImageResources)
}
