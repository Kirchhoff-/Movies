package com.kirchhoff.movies.ui.screens.reviews.list

import android.os.Bundle
import android.view.View
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

    override val configuration: Configuration = Configuration(
        threshold = THRESHOLD,
        spanCount = SPAN_COUNT,
        emptyResultText = R.string.empty_reviews,
        isToolbarVisible = false,
        toolbarTitle = ""
    )

    private val title by lazy { arguments?.getString(REVIEW_TITLE_ARG).orEmpty() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = getString(R.string.review_list_title_format, title)
    }

    override fun onItemClick(item: UIReview) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, ReviewDetailsFragment.newInstance(item, title))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance(
            id: Int,
            reviewType: ReviewType,
            title: String?
        ): ReviewsListFragment = ReviewsListFragment().apply {
            arguments = Bundle().apply {
                putInt(ID_ARG, id)
                putInt(REVIEW_TYPE_ARG, reviewType.ordinal)
                putString(REVIEW_TITLE_ARG, title)
            }
        }

        private const val ID_ARG = "ID_ARG"
        private const val REVIEW_TYPE_ARG = "REVIEW_TYPE_ARG"
        private const val REVIEW_TITLE_ARG = "REVIEW_TITLE_ARG"
        private const val SPAN_COUNT = 1
        private const val THRESHOLD = 3
    }
}
