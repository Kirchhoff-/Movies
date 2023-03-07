package com.kirchhoff.movies.screen.review.ui.screen.list.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.adapter.viewholder.BaseVH
import com.kirchhoff.movies.screen.review.data.UIReview

class ReviewsListAdapter(clickListener: OnItemClickListener<UIReview>) :
    BaseRecyclerViewAdapter<BaseVH<UIReview>, UIReview>(itemClickListener = clickListener) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ReviewVH(parent)
}
