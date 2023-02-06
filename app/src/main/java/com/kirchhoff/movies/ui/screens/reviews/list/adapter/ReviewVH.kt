package com.kirchhoff.movies.ui.screens.reviews.list.adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kirchhoff.movies.R
import com.kirchhoff.movies.core.extensions.downloadAvatar
import com.kirchhoff.movies.core.extensions.inflate
import com.kirchhoff.movies.core.ui.recyclerview.BaseVH
import com.kirchhoff.movies.data.ui.details.review.UIReview

class ReviewVH(parent: ViewGroup) : BaseVH<UIReview>(parent.inflate(R.layout.item_review)) {

    private val tvReviewerName: TextView = itemView.findViewById(R.id.tvReviewerName)
    private val ivReviewerAvatar: ImageView = itemView.findViewById(R.id.ivReviewerAvatar)
    private val tvReviewContent: TextView = itemView.findViewById(R.id.tvReviewContent)

    override fun bind(item: UIReview) {
        tvReviewerName.text = item.author
        ivReviewerAvatar.downloadAvatar(item.authorAvatar)
        tvReviewContent.text = item.content
    }
}
