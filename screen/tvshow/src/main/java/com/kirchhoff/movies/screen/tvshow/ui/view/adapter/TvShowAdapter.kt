package com.kirchhoff.movies.screen.tvshow.ui.view.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.adapter.viewholder.BaseVH

internal class TvShowAdapter(clickListener: OnItemClickListener<UITv>) :
    BaseRecyclerViewAdapter<BaseVH<UITv>, UITv>(itemClickListener = clickListener) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TvShowViewHolder(parent)
}
