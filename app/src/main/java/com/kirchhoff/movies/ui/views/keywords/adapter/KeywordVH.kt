package com.kirchhoff.movies.ui.views.keywords.adapter

import android.view.ViewGroup
import android.widget.TextView
import com.kirchhoff.movies.R
import com.kirchhoff.movies.core.extensions.inflate
import com.kirchhoff.movies.ui.utils.recyclerView.BaseVH

class KeywordVH(parent: ViewGroup) : BaseVH<String>(parent.inflate(R.layout.item_keyword)) {

    private val tvKeyword: TextView = itemView.findViewById(R.id.tvKeyword)

    override fun bind(item: String) {
        tvKeyword.text = item
    }
}
