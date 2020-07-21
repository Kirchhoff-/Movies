package com.kirchhoff.movies.ui.screens.reviews.list

import android.os.Bundle
import android.view.View
import com.kirchhoff.movies.R
import com.kirchhoff.movies.databinding.FragmentReviewsListBinding
import com.kirchhoff.movies.ui.screens.BaseFragment
import com.kirchhoff.movies.utils.binding.viewBinding

class ReviewsListFragment : BaseFragment(R.layout.fragment_reviews_list) {

    private val movieId: Int by lazy { arguments!!.getInt(MOVIE_ID_ARG) }

    private val viewBinding: FragmentReviewsListBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with (viewBinding) {
            tvId.text = movieId.toString()
        }
    }

    companion object {

        fun newInstance(movieId: Int): ReviewsListFragment {
            val fragment = ReviewsListFragment()
            val arg = Bundle()
            arg.putInt(MOVIE_ID_ARG, movieId)
            fragment.arguments = arg
            return fragment
        }

        private const val MOVIE_ID_ARG = "MOVIE_ID_ARG"
    }
}