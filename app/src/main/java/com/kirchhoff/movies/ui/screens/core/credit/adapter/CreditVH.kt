package com.kirchhoff.movies.ui.screens.core.credit.adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.responses.CreditsInfo
import com.kirchhoff.movies.extensions.downloadPoster
import com.kirchhoff.movies.extensions.inflate
import com.kirchhoff.movies.ui.utils.recyclerView.BaseVH

class CreditVH(parent: ViewGroup) : BaseVH<CreditsInfo>(parent.inflate(R.layout.item_credit)) {

    private val ivImage: ImageView = itemView.findViewById(R.id.ivImage)
    private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
    private val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)

    override fun bind(item: CreditsInfo) {
        ivImage.downloadPoster(item.imagePath())
        tvTitle.text = item.title()
        tvDescription.text = item.description()
    }
}
