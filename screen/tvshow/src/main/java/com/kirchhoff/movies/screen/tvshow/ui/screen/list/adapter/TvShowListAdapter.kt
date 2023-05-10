package com.kirchhoff.movies.screen.tvshow.ui.screen.list.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.adapter.viewholder.BaseVH

class TvShowListAdapter(clickListener: OnItemClickListener<UITv>) :
    BaseRecyclerViewAdapter<BaseVH<UITv>, UITv>(itemClickListener = clickListener) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TvShowVH(parent)
}
