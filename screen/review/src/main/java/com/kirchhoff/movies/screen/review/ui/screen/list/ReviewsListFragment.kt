package com.kirchhoff.movies.screen.review.ui.screen.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.TvId
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
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.parameter.parametersOf

internal class ReviewsListFragment : BaseFragment() {

    private val args: ReviewsListArgs by lazy {
        requireArguments().getParcelableExtra(REVIEW_ARGS) ?: error("Review argument is not provided")
    }

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
                onReviewClick = { review, title -> onReviewClick(review, title) },
                onBackPressed = { requireActivity().onBackPressedDispatcher.onBackPressed() }
            )
        }
    }

    override fun onDestroy() {
        unloadKoinModules(reviewModule)
        super.onDestroy()
    }

    private fun onReviewClick(review: UIReview, title: String) {
        reviewRouter.openDetailsScreen(review, title)
    }

    companion object {
        fun newInstanceForMovie(id: MovieId): ReviewsListFragment =
            newInstance(id.value, ReviewType.MOVIE)

        fun newInstanceForTvShow(id: TvId): ReviewsListFragment =
            newInstance(id.value, ReviewType.TV)

        private fun newInstance(
            id: Int,
            reviewType: ReviewType
        ): ReviewsListFragment = ReviewsListFragment().apply {
            arguments = Bundle().apply {
                putParcelable(
                    REVIEW_ARGS,
                    ReviewsListArgs(
                        id = id,
                        reviewType = reviewType
                    )
                )
            }
        }

        private const val REVIEW_ARGS = "REVIEW_ARGS"
    }
}
