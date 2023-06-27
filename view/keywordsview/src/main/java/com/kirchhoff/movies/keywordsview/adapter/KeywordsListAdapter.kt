package com.kirchhoff.movies.keywordsview.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.adapter.viewholder.BaseVH
import com.kirchhoff.movies.keywordsview.data.KeywordsViewData

class KeywordsListAdapter(
    itemClickListener: OnItemClickListener<KeywordsViewData>
) : BaseRecyclerViewAdapter<BaseVH<KeywordsViewData>, KeywordsViewData>(itemClickListener = itemClickListener) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = KeywordVH(parent)
}
