package com.kirchhoff.movies.ui.screens.reviews.details

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.TextView
import com.kirchhoff.movies.R
import com.kirchhoff.movies.ui.screens.BaseFragment

class ReviewDetailsFragment : BaseFragment(R.layout.fragment_review_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val content = arguments!!.getString(CONTENT_ARG)!!

        val tvReviewContent: TextView = view.findViewById(R.id.tvReviewContent)
        tvReviewContent.movementMethod = ScrollingMovementMethod()
        tvReviewContent.text = content
    }

    companion object {
        fun newInstance(content: String): ReviewDetailsFragment {
            val fragment = ReviewDetailsFragment()
            val arg = Bundle()
            arg.putString(CONTENT_ARG, content)
            fragment.arguments = arg
            return fragment
        }

        private const val CONTENT_ARG = "REVIEW_ARG"
    }
}
