package com.kirchhoff.movies.ui.screens.reviews.details

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.TextView
import com.kirchhoff.movies.R
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.data.ui.details.review.UIReview

class ReviewDetailsFragment : BaseFragment(R.layout.fragment_review_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val review = arguments!!.getParcelable<UIReview>(REVIEW_ARG)!!
        val title = arguments?.getString(TITLE_ARG)

        val tvReviewContent: TextView = view.findViewById(R.id.tvReviewContent)
        tvReviewContent.movementMethod = ScrollingMovementMethod()
        tvReviewContent.text = review.content
        requireActivity().title = getString(R.string.review_details_title_format, review.author, title)
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
