package com.kirchhoff.movies.ui.screens.reviews.list

import android.os.Bundle
import com.kirchhoff.movies.R
import com.kirchhoff.movies.core.ui.recyclerview.BaseRecyclerViewAdapter
import com.kirchhoff.movies.data.ui.core.UIPaginated
import com.kirchhoff.movies.data.ui.details.review.UIReview
import com.kirchhoff.movies.ui.screens.core.PaginatedScreenFragment
import com.kirchhoff.movies.ui.screens.reviews.ReviewType
import com.kirchhoff.movies.ui.screens.reviews.details.ReviewDetailsFragment
import com.kirchhoff.movies.ui.screens.reviews.list.adapter.ReviewsListAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ReviewsListFragment : PaginatedScreenFragment<UIReview, UIPaginated<UIReview>>(),
    BaseRecyclerViewAdapter.OnItemClickListener<UIReview> {

    override val vm: ReviewsListVM by viewModel {
        parametersOf(
            arguments!!.getInt(ID_ARG),
            ReviewType.values()[arguments!!.getInt(REVIEW_TYPE_ARG)]
        )
    }

    override val listAdapter = ReviewsListAdapter(this)

    override val threshold = REVIEWS_THRESHOLD

    override val spanCount = SPAN_COUNT

    override fun onItemClick(item: UIReview) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, ReviewDetailsFragment.newInstance(item.content))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance(id: Int, reviewType: ReviewType): ReviewsListFragment {
            val fragment = ReviewsListFragment()
            val arg = Bundle()
            arg.putInt(ID_ARG, id)
            arg.putInt(REVIEW_TYPE_ARG, reviewType.ordinal)
            fragment.arguments = arg
            return fragment
        }

        private const val ID_ARG = "ID_ARG"
        private const val REVIEW_TYPE_ARG = "REVIEW_TYPE_ARG"
        private const val SPAN_COUNT = 1
        private const val REVIEWS_THRESHOLD = 3
    }
}
