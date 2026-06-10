package com.kirchhoff.movies.screen.review.ui.screen.details

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.net.toUri
import com.kirchhoff.movies.core.extensions.getParcelableExtra
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.screen.review.data.ReviewUIInfo

internal class ReviewDetailsFragment : BaseFragment() {

    private val review by lazy {
        requireArguments().getParcelableExtra<ReviewUIInfo>(REVIEW_ARG) ?: error("review argument is not provided")
    }
    private val title by lazy { requireArguments().getString(TITLE_ARG).orEmpty() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            ReviewDetailsUI(
                title = title,
                review = review,
                onOpenInBrowserClick = { url -> startActivity(Intent(Intent.ACTION_VIEW, url.toUri())) },
                onBackPressed = { requireActivity().onBackPressedDispatcher.onBackPressed() }
            )
        }
    }

    companion object {
        fun newInstance(
            review: ReviewUIInfo,
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
