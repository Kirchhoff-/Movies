package com.kirchhoff.movies.ui.screens.core.movies.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.data.Movie
import com.kirchhoff.movies.ui.utils.recyclerView.BaseRecyclerViewAdapter
import com.kirchhoff.movies.ui.utils.recyclerView.BaseVH

class MoviesListAdapter(clickListener: BaseRecyclerViewAdapter.OnItemClickListener<Movie>) :
    BaseRecyclerViewAdapter<BaseVH<Movie>, Movie>(itemClickListener = clickListener) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieVH(parent)
}
