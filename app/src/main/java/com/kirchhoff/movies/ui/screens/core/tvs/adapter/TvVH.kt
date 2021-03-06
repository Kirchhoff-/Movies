package com.kirchhoff.movies.ui.screens.core.tvs.adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.ui.main.UITv
import com.kirchhoff.movies.extensions.downloadPoster
import com.kirchhoff.movies.extensions.inflate
import com.kirchhoff.movies.ui.utils.recyclerView.BaseVH

class TvVH(parent: ViewGroup) : BaseVH<UITv>(parent.inflate(R.layout.item_tv)) {

    private val ivTvPoster: ImageView = itemView.findViewById(R.id.ivTvPoster)
    private val tvTvTitle: TextView = itemView.findViewById(R.id.tvTvTitle)

    override fun bind(item: UITv) {
        tvTvTitle.text = item.name
        ivTvPoster.downloadPoster(item.posterPath)
    }
}
