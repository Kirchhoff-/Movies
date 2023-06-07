package com.kirchhoff.movies.screen.movie.ui.view.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.adapter.viewholder.BaseVH

class MovieAdapter(clickListener: OnItemClickListener<UIMovie>) :
    BaseRecyclerViewAdapter<BaseVH<UIMovie>, UIMovie>(itemClickListener = clickListener) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(parent)
}
