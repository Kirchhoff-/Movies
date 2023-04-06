package com.kirchhoff.movies.screen.review.ui.screen.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.kirchhoff.movies.core.extensions.getParcelableExtra
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.screen.review.data.UIReview

internal class ReviewDetailsFragment : BaseFragment() {

    private val review by lazy { requireArguments().getParcelableExtra<UIReview>(REVIEW_ARG)!! }
    private val title by lazy { requireArguments().getString(TITLE_ARG).orEmpty() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            ReviewDetailsUI(title = title, review = review) {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
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
