package com.kirchhoff.movies.screen.review.ui.screen.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import com.kirchhoff.movies.core.extensions.getParcelableExtra
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.screen.review.data.UIReview
import com.kirchhoff.movies.screen.review.reviewModule
import com.kirchhoff.movies.screen.review.router.IReviewRouter
import com.kirchhoff.movies.screen.review.ui.screen.ReviewType
import com.kirchhoff.movies.screen.review.ui.screen.list.model.ReviewsListArgs
import com.kirchhoff.movies.screen.review.ui.screen.list.ui.ReviewListUI
import com.kirchhoff.movies.screen.review.ui.screen.list.viewmodel.ReviewsListViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

class ReviewsListFragment : BaseFragment() {

    private val args: ReviewsListArgs by lazy { requireArguments().getParcelableExtra(REVIEW_ARGS)!! }

    private val reviewRouter: IReviewRouter by inject { parametersOf(requireActivity()) }

    private val viewModel: ReviewsListViewModel by viewModel {
        parametersOf(args)
    }

    override fun onAttach(context: Context) {
        loadKoinModules(reviewModule)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadReviews()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            val screenState by viewModel.screenState.observeAsState()

            ReviewListUI(
                screenState ?: error("Can't build UI without state"),
                onLoadMore = { viewModel.loadReviews() },
                onReviewClick = { onReviewClick(it) },
                onBackPressed = { requireActivity().onBackPressedDispatcher.onBackPressed() }
            )
        }
    }

    override fun onDestroy() {
        unloadKoinModules(reviewModule)
        super.onDestroy()
    }

    private fun onReviewClick(review: UIReview) {
        reviewRouter.openDetailsScreen(review, args.title)
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
                putParcelable(
                    REVIEW_ARGS,
                    ReviewsListArgs(
                        id = id,
                        title = title.orEmpty(),
                        reviewType = reviewType
                    )
                )
            }
        }

        private const val REVIEW_ARGS = "REVIEW_ARGS"
    }
}
