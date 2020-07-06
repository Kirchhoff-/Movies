package com.kirchhoff.movies.ui.screens.main.movies.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.data.Movie
import com.kirchhoff.movies.ui.utils.recyclerView.BaseRecyclerViewAdapter

class MoviesListAdapter : BaseRecyclerViewAdapter<MovieVH, Movie>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieVH(parent)
}
