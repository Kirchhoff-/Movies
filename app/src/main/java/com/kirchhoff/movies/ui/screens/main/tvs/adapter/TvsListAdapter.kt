package com.kirchhoff.movies.ui.screens.main.tvs.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.data.Tv
import com.kirchhoff.movies.ui.utils.recyclerView.BaseRecyclerViewAdapter

class TvsListAdapter : BaseRecyclerViewAdapter<TvVH, Tv>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TvVH(parent)
}
