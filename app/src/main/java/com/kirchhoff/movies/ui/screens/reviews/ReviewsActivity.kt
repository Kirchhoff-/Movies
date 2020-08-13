package com.kirchhoff.movies.ui.screens.reviews

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.ui.main.UIMovie
import com.kirchhoff.movies.data.ui.main.UITv
import com.kirchhoff.movies.databinding.ActivityReviewBinding
import com.kirchhoff.movies.ui.screens.reviews.list.ReviewsListFragment
import com.kirchhoff.movies.utils.binding.viewBinding

class ReviewsActivity : AppCompatActivity(R.layout.activity_review) {

    private val viewBinding: ActivityReviewBinding by viewBinding(R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val reviewType: ReviewType =
            ReviewType.values()[intent.extras!!.getInt(REVIEW_TYPE_ARG)]

        when (reviewType) {
            ReviewType.MOVIE -> movieReview()
            ReviewType.TV -> tvReview()
        }

        viewBinding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun movieReview() {
        val movie: UIMovie = intent.getParcelableExtra(MOVIE_ARG)!!

        viewBinding.toolbar.title = movie.title
        openReviews(ReviewsListFragment.newInstance(movie.id, ReviewType.MOVIE))
    }

    private fun tvReview() {
        val tv: UITv = intent.getParcelableExtra(TV_ARG)!!

        viewBinding.toolbar.title = tv.name
        openReviews(ReviewsListFragment.newInstance(tv.id, ReviewType.TV))
    }

    private fun openReviews(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    companion object {

        fun createReviewsIntent(context: Context, movie: UIMovie): Intent {
            val intent = Intent(context, ReviewsActivity::class.java)
            intent.putExtra(REVIEW_TYPE_ARG, ReviewType.MOVIE.ordinal)
            intent.putExtra(MOVIE_ARG, movie)
            return intent
        }

        fun createTvIntent(context: Context, tv: UITv): Intent {
            val intent = Intent(context, ReviewsActivity::class.java)
            intent.putExtra(REVIEW_TYPE_ARG, ReviewType.TV.ordinal)
            intent.putExtra(TV_ARG, tv)
            return intent
        }

        private const val REVIEW_TYPE_ARG = "REVIEW_TYPE_ARG"
        private const val MOVIE_ARG = "MOVIE_ARG"
        private const val TV_ARG = "TV_ARG"
    }
}
