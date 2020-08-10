package com.kirchhoff.movies.ui.screens.reviews.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.ui.details.review.UIReview
import com.kirchhoff.movies.data.ui.details.review.UIReviewsListResponse
import com.kirchhoff.movies.databinding.FragmentReviewsListBinding
import com.kirchhoff.movies.extensions.getSizeFromRes
import com.kirchhoff.movies.ui.screens.BaseFragment
import com.kirchhoff.movies.ui.screens.reviews.ReviewType
import com.kirchhoff.movies.ui.screens.reviews.details.ReviewDetailsFragment
import com.kirchhoff.movies.ui.screens.reviews.list.adapter.ReviewsListAdapter
import com.kirchhoff.movies.ui.utils.recyclerView.BaseRecyclerViewAdapter
import com.kirchhoff.movies.ui.utils.recyclerView.Paginator
import com.kirchhoff.movies.ui.utils.recyclerView.decorations.GridMarginItemDecoration
import com.kirchhoff.movies.utils.binding.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ReviewsListFragment : BaseFragment(R.layout.fragment_reviews_list),
    BaseRecyclerViewAdapter.OnItemClickListener<UIReview> {

    private val dataId: Int by lazy { arguments!!.getInt(ID_ARG) }
    private val reviewType: ReviewType by lazy { ReviewType.values()[arguments!!.getInt(REVIEW_TYPE_ARG)] }

    private val vm by viewModel<ReviewsListVM>()

    private val viewBinding: FragmentReviewsListBinding by viewBinding()

    private val paginator = Paginator(loadMore = { loadReviews(it) }, threshold = REVIEWS_THRESHOLD)

    private val reviewsAdapter = ReviewsListAdapter(this)

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

        with(vm) {
            loading.subscribe { viewBinding.pbLoading.isVisible = it }
            paginating.subscribe { viewBinding.pbPaginate.isVisible = it }
            data.subscribe(::obtainReviewsResponse)
            error.subscribe { Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show() }
        }
    }

    private fun loadReviews(page: Int) {
        vm.fetchReviews(dataId, reviewType, page)
        paginator.isLoading = true
    }

    private fun obtainReviewsResponse(response: UIReviewsListResponse) {
        reviewsAdapter.addItems(response.results)
        paginator.totalPages = response.totalPages
        paginator.isLoading = false
    }

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
