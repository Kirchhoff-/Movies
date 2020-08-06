package com.kirchhoff.movies.ui.screens.details.movie.adapters

import android.view.ViewGroup
import com.kirchhoff.movies.data.ui.details.movie.UITrailer
import com.kirchhoff.movies.ui.utils.recyclerView.BaseRecyclerViewAdapter
import com.kirchhoff.movies.ui.utils.recyclerView.BaseVH

class TrailersListAdapter(trailersList: List<UITrailer>, clickListener: OnItemClickListener<UITrailer>) :
    BaseRecyclerViewAdapter<BaseVH<UITrailer>, UITrailer>(itemClickListener = clickListener) {

    init {
        addItems(trailersList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TrailerVH(parent)
}
