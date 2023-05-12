package com.kirchhoff.movies.screen.movie.ui.screen.details.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.adapter.viewholder.BaseVH
import com.kirchhoff.movies.screen.movie.data.UITrailer

class MovieTrailerListAdapter(trailersList: List<UITrailer>, clickListener: OnItemClickListener<UITrailer>) :
    BaseRecyclerViewAdapter<BaseVH<UITrailer>, UITrailer>(itemClickListener = clickListener) {

    init {
        addItems(trailersList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieTrailerVH(parent)
}
