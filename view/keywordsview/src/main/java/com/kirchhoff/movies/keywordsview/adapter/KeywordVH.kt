package com.kirchhoff.movies.keywordsview.adapter

import android.view.ViewGroup
import android.widget.TextView
import com.kirchhoff.movies.core.extensions.inflate
import com.kirchhoff.movies.core.ui.recyclerview.adapter.viewholder.BaseVH
import com.kirchhoff.movies.keywordsview.R
import com.kirchhoff.movies.keywordsview.data.KeywordsViewData

class KeywordVH(parent: ViewGroup) : BaseVH<KeywordsViewData>(parent.inflate(R.layout.item_keyword)) {

    private val tvKeyword: TextView = itemView.findViewById(R.id.tvKeyword)

    override fun bind(item: KeywordsViewData) {
        tvKeyword.text = item.displayedValue
    }
}
