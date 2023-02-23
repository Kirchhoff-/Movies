package com.kirchhoff.movies.ui.screens.reviews.details

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import com.kirchhoff.movies.R
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.core.ui.utils.viewBinding
import com.kirchhoff.movies.data.ui.details.review.UIReview
import com.kirchhoff.movies.databinding.FragmentReviewDetailsBinding

class ReviewDetailsFragment : BaseFragment(R.layout.fragment_review_details) {

    private val viewBinding by viewBinding(FragmentReviewDetailsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val review = requireArguments().getParcelable<UIReview>(REVIEW_ARG)!!
        val title = requireArguments().getString(TITLE_ARG)

        with(viewBinding) {
            tvReviewContent.movementMethod = ScrollingMovementMethod()
            tvReviewContent.text = review.content
            toolbarReviewDetails.title = getString(R.string.review_details_title_format, review.author, title)
            toolbarReviewDetails.setNavigationOnClickListener { requireActivity().onBackPressed() }
        }
    }

    companion object {
        fun newInstance(
            review: UIReview,
            title: String
        ): ReviewDetailsFragment = ReviewDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REVIEW_ARG, review)
                putString(TITLE_ARG, title)
            }
        }

        private const val REVIEW_ARG = "REVIEW_ARG"
        private const val TITLE_ARG = "TITLE_ARG"
    }
}
