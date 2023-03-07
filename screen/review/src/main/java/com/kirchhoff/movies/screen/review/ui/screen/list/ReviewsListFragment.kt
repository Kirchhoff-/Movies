package com.kirchhoff.movies.screen.review.ui.screen.list

import android.content.Context
import android.os.Bundle
import android.view.View
import com.kirchhoff.movies.core.ui.paginated.PaginatedScreenFragment
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.screen.review.R
import com.kirchhoff.movies.screen.review.data.UIReview
import com.kirchhoff.movies.screen.review.reviewModule
import com.kirchhoff.movies.screen.review.ui.screen.ReviewType
import com.kirchhoff.movies.screen.review.ui.screen.details.ReviewDetailsFragment
import com.kirchhoff.movies.screen.review.ui.screen.list.adapter.ReviewsListAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

class ReviewsListFragment : PaginatedScreenFragment<UIReview, UIPaginated<UIReview>>(),
    BaseRecyclerViewAdapter.OnItemClickListener<UIReview> {

    override val vm: ReviewsListVM by viewModel {
        parametersOf(
            requireArguments().getInt(ID_ARG),
            ReviewType.values()[requireArguments().getInt(REVIEW_TYPE_ARG)]
        )
    }

    override val listAdapter = ReviewsListAdapter(this)

    override val configuration: Configuration = Configuration(
        threshold = THRESHOLD,
        spanCount = SPAN_COUNT,
        emptyResultText = R.string.empty_reviews,
        isToolbarVisible = true,
        toolbarTitle = ""
    )

    private val title by lazy { requireArguments().getString(REVIEW_TITLE_ARG).orEmpty() }

    override fun onAttach(context: Context) {
        loadKoinModules(reviewModule)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayTitle(getString(R.string.review_list_title_format, title))
    }

    override fun onDestroy() {
        unloadKoinModules(reviewModule)
        super.onDestroy()
    }

    override fun onItemClick(item: UIReview) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, ReviewDetailsFragment.newInstance(item, title))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstanceForMovie(id: Int, title: String?): ReviewsListFragment =
            newInstance(id, ReviewType.MOVIE, title)

        fun newInstanceForTvShow(id: Int, title: String?): ReviewsListFragment =
            newInstance(id, ReviewType.TV, title)

        private fun newInstance(
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