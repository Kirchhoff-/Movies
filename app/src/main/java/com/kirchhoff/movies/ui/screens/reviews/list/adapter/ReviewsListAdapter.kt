package com.kirchhoff.movies.ui.screens.reviews.list.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.data.ui.details.review.UIReview
import com.kirchhoff.movies.ui.utils.recyclerView.BaseRecyclerViewAdapter

class ReviewsListAdapter(clickListener: OnItemClickListener<UIReview>) :
    BaseRecyclerViewAdapter<ReviewVH, UIReview>(itemClickListener = clickListener) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ReviewVH(parent)
}
