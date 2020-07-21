package com.kirchhoff.movies.ui.screens.reviews.list.adapter

import android.view.ViewGroup
import android.widget.TextView
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.Review
import com.kirchhoff.movies.extensions.inflate
import com.kirchhoff.movies.ui.utils.recyclerView.BaseVH

class ReviewVH(parent: ViewGroup) : BaseVH<Review>(parent.inflate(R.layout.item_review)) {

    private val tvReviewerName: TextView = itemView.findViewById(R.id.tvReviewerName)
    private val tvReviewContent: TextView = itemView.findViewById(R.id.tvReviewContent)

    override fun bind(item: Review) {
        tvReviewerName.text = item.author
        tvReviewContent.text = item.content
    }
}
