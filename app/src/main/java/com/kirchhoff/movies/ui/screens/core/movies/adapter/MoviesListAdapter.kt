package com.kirchhoff.movies.ui.screens.core.movies.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.adapter.viewholder.BaseVH
import com.kirchhoff.movies.data.ui.main.UIMovie

class MoviesListAdapter(clickListener: OnItemClickListener<UIMovie>) :
    BaseRecyclerViewAdapter<BaseVH<UIMovie>, UIMovie>(itemClickListener = clickListener) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieVH(parent)
}
