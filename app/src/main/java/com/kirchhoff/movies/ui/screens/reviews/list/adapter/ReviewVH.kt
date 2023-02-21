package com.kirchhoff.movies.ui.screens.reviews.list.adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import com.kirchhoff.movies.R
import com.kirchhoff.movies.core.extensions.downloadAvatar
import com.kirchhoff.movies.core.extensions.inflate
import com.kirchhoff.movies.core.ui.recyclerview.adapter.viewholder.BaseVH
import com.kirchhoff.movies.data.ui.details.review.UIReview

class ReviewVH(parent: ViewGroup) : BaseVH<UIReview>(parent.inflate(R.layout.item_review)) {

    private val tvReviewerName: TextView = itemView.findViewById(R.id.tvReviewerName)
    private val ivReviewerAvatar: ImageView = itemView.findViewById(R.id.ivReviewerAvatar)
    private val tvReviewContent: TextView = itemView.findViewById(R.id.tvReviewContent)
    private val rbReviewRating: RatingBar = itemView.findViewById(R.id.rbReviewRating)
    private val tvReviewRating: TextView = itemView.findViewById(R.id.tvReviewRating)
    private val groupRating: Group = itemView.findViewById(R.id.groupRating)

    override fun bind(item: UIReview) {
        tvReviewerName.text = item.author
        ivReviewerAvatar.downloadAvatar(item.authorAvatar)
        tvReviewContent.text = item.content
        groupRating.isVisible = item.rating != null
        if (item.rating != null) {
            tvReviewRating.text =
                itemView.resources.getString(R.string.review_rating_format, item.rating)
            rbReviewRating.rating = item.rating.toFloat()
        }
    }
}
