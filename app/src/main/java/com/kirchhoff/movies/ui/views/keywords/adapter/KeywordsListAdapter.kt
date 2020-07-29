package com.kirchhoff.movies.ui.views.keywords.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.ui.utils.recyclerView.BaseRecyclerViewAdapter
import com.kirchhoff.movies.ui.utils.recyclerView.BaseVH

class KeywordsListAdapter : BaseRecyclerViewAdapter<BaseVH<String>, String>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = KeywordVH(parent)
}
