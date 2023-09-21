package com.kirchhoff.movies.creditsview.adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kirchhoff.movies.core.extensions.downloadAvatar
import com.kirchhoff.movies.core.extensions.inflate
import com.kirchhoff.movies.core.ui.recyclerview.adapter.viewholder.BaseVH
import com.kirchhoff.movies.creditsview.R
import com.kirchhoff.movies.creditsview.data.CreditsInfo

class CreditVH(parent: ViewGroup) : BaseVH<CreditsInfo>(parent.inflate(R.layout.item_credit)) {

    private val ivImage: ImageView = itemView.findViewById(R.id.ivImage)
    private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
    private val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)

    override fun bind(item: CreditsInfo) {
        ivImage.downloadAvatar(item.imagePath)
        tvTitle.text = item.title
        tvDescription.text = item.description
    }
}
