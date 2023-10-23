package com.kirchhoff.movies.screen.movie.ui.screen.details.view.similar.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.adapter.viewholder.BaseVH

internal class MovieDetailsSimilarMovieAdapter(clickListener: OnItemClickListener<UIMovie>) :
    BaseRecyclerViewAdapter<BaseVH<UIMovie>, UIMovie>(itemClickListener = clickListener) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH<UIMovie> =
        MovieDetailsSimilarMovieVH(parent)
}
