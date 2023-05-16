package com.kirchhoff.movies.screen.similar.ui.screen.tv.viewmodel.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.adapter.viewholder.BaseVH

class SimilarTvsAdapter(clickListener: OnItemClickListener<UITv>) :
    BaseRecyclerViewAdapter<BaseVH<UITv>, UITv>(itemClickListener = clickListener) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SimilarTvsViewHolder(parent)
}
