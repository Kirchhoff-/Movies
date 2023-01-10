package com.kirchhoff.movies.ui.screens.reviews.list.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.core.ui.recyclerview.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.BaseVH
import com.kirchhoff.movies.data.ui.details.review.UIReview

class ReviewsListAdapter(clickListener: OnItemClickListener<UIReview>) :
    BaseRecyclerViewAdapter<BaseVH<UIReview>, UIReview>(itemClickListener = clickListener) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ReviewVH(parent)
}
