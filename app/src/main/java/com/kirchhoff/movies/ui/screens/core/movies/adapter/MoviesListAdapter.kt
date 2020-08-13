package com.kirchhoff.movies.ui.screens.core.movies.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.data.ui.main.UIMovie
import com.kirchhoff.movies.ui.utils.recyclerView.BaseRecyclerViewAdapter
import com.kirchhoff.movies.ui.utils.recyclerView.BaseVH

class MoviesListAdapter(clickListener: OnItemClickListener<UIMovie>) :
    BaseRecyclerViewAdapter<BaseVH<UIMovie>, UIMovie>(itemClickListener = clickListener) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieVH(parent)
}
