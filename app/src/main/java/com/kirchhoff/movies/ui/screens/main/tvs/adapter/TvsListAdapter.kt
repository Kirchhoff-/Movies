package com.kirchhoff.movies.ui.screens.main.tvs.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.data.Tv
import com.kirchhoff.movies.ui.utils.recyclerView.BaseRecyclerViewAdapter
import com.kirchhoff.movies.ui.utils.recyclerView.BaseVH

class TvsListAdapter(clickListener: OnItemClickListener<Tv>) :
    BaseRecyclerViewAdapter<BaseVH<Tv>, Tv>(itemClickListener = clickListener) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TvVH(parent)
}
