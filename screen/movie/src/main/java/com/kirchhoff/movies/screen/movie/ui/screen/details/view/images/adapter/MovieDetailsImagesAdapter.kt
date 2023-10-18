package com.kirchhoff.movies.screen.movie.ui.screen.details.view.images.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.core.data.UIImage
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.adapter.viewholder.BaseVH

internal class MovieDetailsImagesAdapter(clickListener: OnItemClickListener<UIImage>) :
    BaseRecyclerViewAdapter<BaseVH<UIImage>, UIImage>(itemClickListener = clickListener) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH<UIImage> =
        MovieDetailsImageVH(parent)
}
