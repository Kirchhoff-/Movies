package com.kirchhoff.movies.screen.movie.ui.screen.details.adapter

import android.view.ViewGroup
import android.widget.ImageView
import com.kirchhoff.movies.core.extensions.downloadYoutubePoster
import com.kirchhoff.movies.core.extensions.inflate
import com.kirchhoff.movies.core.ui.recyclerview.adapter.viewholder.BaseVH
import com.kirchhoff.movies.screen.movie.R
import com.kirchhoff.movies.screen.movie.data.UITrailer

class MovieTrailerVH(parent: ViewGroup) : BaseVH<UITrailer>(parent.inflate(R.layout.item_trailer)) {

    private val ivTrailerPoster: ImageView = itemView.findViewById(R.id.ivTrailerPoster)
    private val ivTrailerSourceIcon: ImageView = itemView.findViewById(R.id.ivTrailerSourceIcon)

    override fun bind(item: UITrailer) {
        ivTrailerPoster.downloadYoutubePoster(item.key)

        val resultIcon = if (item.site.equals(YOUTUBE, true)) {
            com.kirchhoff.movies.core.R.drawable.ic_youtube
        } else {
            com.kirchhoff.movies.core.R.drawable.ic_play_arrow
        }
        ivTrailerSourceIcon.setImageResource(resultIcon)
    }

    companion object {
        private const val YOUTUBE = "youtube"
    }
}
