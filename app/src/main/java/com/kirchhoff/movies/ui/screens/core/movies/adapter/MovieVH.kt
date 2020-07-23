package com.kirchhoff.movies.ui.screens.core.movies.adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.Movie
import com.kirchhoff.movies.extensions.downloadPoster
import com.kirchhoff.movies.extensions.inflate
import com.kirchhoff.movies.ui.utils.recyclerView.BaseVH

class MovieVH(parent: ViewGroup) : BaseVH<Movie>(parent.inflate(R.layout.item_movie)) {

    private val ivMoviePoster: ImageView = itemView.findViewById(R.id.ivMoviePoster)
    private val tvMovieTitle: TextView = itemView.findViewById(R.id.tvMovieTitle)

    override fun bind(item: Movie) {
        tvMovieTitle.text = item.title
        ivMoviePoster.downloadPoster(item.poster_path)
    }
}
