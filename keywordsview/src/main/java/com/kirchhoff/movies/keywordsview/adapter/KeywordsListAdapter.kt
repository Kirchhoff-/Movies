package com.kirchhoff.movies.keywordsview.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.core.ui.recyclerview.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.BaseVH

class KeywordsListAdapter : BaseRecyclerViewAdapter<BaseVH<String>, String>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = KeywordVH(parent)
}
