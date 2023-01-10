package com.kirchhoff.movies.ui.screens.core.movies.adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kirchhoff.movies.R
import com.kirchhoff.movies.core.extensions.downloadPoster
import com.kirchhoff.movies.core.extensions.inflate
import com.kirchhoff.movies.core.ui.recyclerview.BaseVH
import com.kirchhoff.movies.data.ui.main.UIMovie

class MovieVH(parent: ViewGroup) : BaseVH<UIMovie>(parent.inflate(R.layout.item_movie)) {

    private val ivMoviePoster: ImageView = itemView.findViewById(R.id.ivMoviePoster)
    private val tvMovieTitle: TextView = itemView.findViewById(R.id.tvMovieTitle)

    override fun bind(item: UIMovie) {
        tvMovieTitle.text = item.title
        ivMoviePoster.downloadPoster(item.posterPath)
    }
}
