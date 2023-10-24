package com.kirchhoff.movies.screen.tvshow.ui.view.adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.extensions.downloadPoster
import com.kirchhoff.movies.core.extensions.inflate
import com.kirchhoff.movies.core.ui.recyclerview.adapter.viewholder.BaseVH
import com.kirchhoff.movies.keywordsview.RatingTextView
import com.kirchhoff.movies.screen.tvshow.R

internal class TvShowViewHolder(parent: ViewGroup) : BaseVH<UITv>(parent.inflate(R.layout.item_tv_show)) {

    private val ivTvPoster: ImageView = itemView.findViewById(R.id.ivTvPoster)
    private val tvTvTitle: TextView = itemView.findViewById(R.id.tvTvTitle)
    private val tvTvRating: RatingTextView = itemView.findViewById(R.id.tvTvRating)

    override fun bind(item: UITv) {
        tvTvTitle.text = item.name
        ivTvPoster.downloadPoster(item.posterPath)
        tvTvRating.displayRating(item.voteAverage)
    }
}
