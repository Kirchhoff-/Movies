package com.kirchhoff.movies.ui.screens.core.tvs.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.adapter.viewholder.BaseVH
import com.kirchhoff.movies.data.ui.main.UITv

class TvsListAdapter(clickListener: OnItemClickListener<UITv>) :
    BaseRecyclerViewAdapter<BaseVH<UITv>, UITv>(itemClickListener = clickListener) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TvVH(parent)
}
