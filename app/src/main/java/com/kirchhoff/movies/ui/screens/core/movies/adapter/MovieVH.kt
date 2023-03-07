package com.kirchhoff.movies.ui.screens.core.movies.adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.kirchhoff.movies.R
import com.kirchhoff.movies.core.extensions.downloadPoster
import com.kirchhoff.movies.core.extensions.inflate
import com.kirchhoff.movies.core.ui.recyclerview.adapter.viewholder.BaseVH
import com.kirchhoff.movies.data.ui.main.UIMovie
import java.text.DecimalFormat

class MovieVH(parent: ViewGroup) : BaseVH<UIMovie>(parent.inflate(R.layout.item_movie)) {

    private val ivMoviePoster: ImageView = itemView.findViewById(R.id.ivMoviePoster)
    private val tvMovieTitle: TextView = itemView.findViewById(R.id.tvMovieTitle)
    private val tvMovieRating: TextView = itemView.findViewById(R.id.tvMovieRating)

    override fun bind(item: UIMovie) {
        tvMovieTitle.text = item.title
        ivMoviePoster.downloadPoster(item.posterPath)
        tvMovieRating.isVisible = item.voteAverage != null
        tvMovieRating.text = DecimalFormat("#.0").format(item.voteAverage)
    }
}
