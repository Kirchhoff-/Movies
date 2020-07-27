package com.kirchhoff.movies.ui.screens.details.movie.adapters

import android.view.ViewGroup
import com.kirchhoff.movies.data.Trailer
import com.kirchhoff.movies.ui.utils.recyclerView.BaseRecyclerViewAdapter
import com.kirchhoff.movies.ui.utils.recyclerView.BaseVH

class TrailersListAdapter(trailersList: List<Trailer>, clickListener: OnItemClickListener<Trailer>) :
    BaseRecyclerViewAdapter<BaseVH<Trailer>, Trailer>(itemClickListener = clickListener) {

    init {
        addItems(trailersList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TrailerVH(parent)
}
