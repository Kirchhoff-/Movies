package com.kirchhoff.movies.ui.screens.details.movie.adapters

import android.view.ViewGroup
import android.widget.ImageView
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.ui.details.movie.UITrailer
import com.kirchhoff.movies.extensions.downloadYoutubePoster
import com.kirchhoff.movies.extensions.inflate
import com.kirchhoff.movies.ui.utils.recyclerView.BaseVH

class TrailerVH(parent: ViewGroup) : BaseVH<UITrailer>(parent.inflate(R.layout.item_trailer)) {

    private val ivTrailerPoster: ImageView = itemView.findViewById(R.id.ivTrailerPoster)
    private val ivTrailerSourceIcon: ImageView = itemView.findViewById(R.id.ivTrailerSourceIcon)

    override fun bind(item: UITrailer) {
        ivTrailerPoster.downloadYoutubePoster(item.key)

        val resultIcon = if (item.site.equals(YOUTUBE, true)) {
            R.drawable.ic_youtube
        } else {
            R.drawable.ic_play_arrow
        }
        ivTrailerSourceIcon.setImageResource(resultIcon)
    }

    companion object {
        private const val YOUTUBE = "youtube"
    }
}
