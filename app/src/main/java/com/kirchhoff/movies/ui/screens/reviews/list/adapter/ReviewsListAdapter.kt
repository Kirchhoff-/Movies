package com.kirchhoff.movies.ui.screens.reviews.list.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.data.Review
import com.kirchhoff.movies.ui.utils.recyclerView.BaseRecyclerViewAdapter

class ReviewsListAdapter(clickListener: OnItemClickListener<Review>) :
    BaseRecyclerViewAdapter<ReviewVH, Review>(itemClickListener = clickListener) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ReviewVH(parent)
}
