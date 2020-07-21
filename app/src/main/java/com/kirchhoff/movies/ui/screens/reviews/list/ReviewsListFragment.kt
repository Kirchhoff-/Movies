package com.kirchhoff.movies.ui.screens.reviews.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.Movie
import com.kirchhoff.movies.data.responses.ReviewsListResponse
import com.kirchhoff.movies.databinding.FragmentReviewsListBinding
import com.kirchhoff.movies.extensions.getSizeFromRes
import com.kirchhoff.movies.ui.screens.BaseFragment
import com.kirchhoff.movies.ui.screens.reviews.list.adapter.ReviewsListAdapter
import com.kirchhoff.movies.ui.utils.recyclerView.Paginator
import com.kirchhoff.movies.ui.utils.recyclerView.decorations.GridMarginItemDecoration
import com.kirchhoff.movies.utils.binding.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ReviewsListFragment : BaseFragment(R.layout.fragment_reviews_list) {

    private val movie: Movie by lazy { arguments!!.getParcelable<Movie>(MOVIE_ARG)!! }

    private val vm by viewModel<ReviewsListVM>()

    private val viewBinding: FragmentReviewsListBinding by viewBinding()

    private val paginator = Paginator(loadMore = { loadReviews(it) }, threshold = REVIEWS_THRESHOLD)

    private val reviewsAdapter = ReviewsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadReviews(1)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.rvReviews.apply {
            layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
            adapter = reviewsAdapter
            addItemDecoration(
                GridMarginItemDecoration(
                    SPAN_COUNT,
                    getSizeFromRes(R.dimen.reviews_list_screen_item_top_margin),
                    getSizeFromRes(R.dimen.reviews_list_screen_item_bottom_margin),
                    getSizeFromRes(R.dimen.reviews_list_screen_item_edges_margin)
                )
            )
            addOnScrollListener(paginator)
        }

        viewBinding.toolbar.apply {
            title = movie.title
            setNavigationOnClickListener { requireActivity().onBackPressed() }
        }

        with(vm) {
            loading.subscribe { viewBinding.pbLoading.isVisible = it }
            paginating.subscribe { viewBinding.pbPaginate.isVisible = it }
            data.subscribe(::obtainReviewsResponse)
            error.subscribe { Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show() }
        }
    }

    private fun loadReviews(page: Int) {
        vm.fetchReviews(movie.id, page)
        paginator.isLoading = true
    }

    private fun obtainReviewsResponse(response: ReviewsListResponse) {
        reviewsAdapter.addItems(response.results)
        paginator.totalPages = response.total_pages
        paginator.isLoading = false
    }

    companion object {

        fun newInstance(movie: Movie): ReviewsListFragment {
            val fragment = ReviewsListFragment()
            val arg = Bundle()
            arg.putParcelable(MOVIE_ARG, movie)
            fragment.arguments = arg
            return fragment
        }

        private const val MOVIE_ARG = "MOVIE_ARG"
        private const val SPAN_COUNT = 1
        private const val REVIEWS_THRESHOLD = 3
    }
}
