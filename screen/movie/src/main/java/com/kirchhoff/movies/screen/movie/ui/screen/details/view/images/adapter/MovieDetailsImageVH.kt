package com.kirchhoff.movies.screen.movie.ui.screen.details.view.images.adapter

import android.view.ViewGroup
import android.widget.ImageView
import com.kirchhoff.movies.core.data.UIImage
import com.kirchhoff.movies.core.extensions.downloadPoster
import com.kirchhoff.movies.core.extensions.inflate
import com.kirchhoff.movies.core.ui.recyclerview.adapter.viewholder.BaseVH
import com.kirchhoff.movies.screen.movie.R

internal class MovieDetailsImageVH(parent: ViewGroup) :
    BaseVH<UIImage>(parent.inflate(R.layout.item_movie_details_image)) {

    private val ivImage: ImageView = itemView.findViewById(R.id.ivImage)

    override fun bind(item: UIImage) {
        ivImage.downloadPoster(item.path)
    }
}
