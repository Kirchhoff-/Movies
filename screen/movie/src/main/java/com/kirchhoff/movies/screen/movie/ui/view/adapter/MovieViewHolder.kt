package com.kirchhoff.movies.screen.movie.ui.view.adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.extensions.downloadPoster
import com.kirchhoff.movies.core.extensions.inflate
import com.kirchhoff.movies.core.ui.recyclerview.adapter.viewholder.BaseVH
import com.kirchhoff.movies.keywordsview.RatingTextView
import com.kirchhoff.movies.screen.movie.R

internal class MovieViewHolder(parent: ViewGroup) : BaseVH<UIMovie>(parent.inflate(R.layout.item_movie)) {

    private val ivMoviePoster: ImageView = itemView.findViewById(R.id.ivMoviePoster)
    private val tvMovieTitle: TextView = itemView.findViewById(R.id.tvMovieTitle)
    private val tvMovieRating: RatingTextView = itemView.findViewById(R.id.tvMovieRating)

    override fun bind(item: UIMovie) {
        tvMovieTitle.text = item.title
        ivMoviePoster.downloadPoster(item.posterPath)
        tvMovieRating.displayRating(item.voteAverage)
    }
}
