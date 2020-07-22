package com.kirchhoff.movies.ui.screens.reviews

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.Movie
import com.kirchhoff.movies.databinding.ActivityReviewBinding
import com.kirchhoff.movies.ui.screens.reviews.list.ReviewsListFragment
import com.kirchhoff.movies.utils.binding.viewBinding

class ReviewsActivity : AppCompatActivity(R.layout.activity_review) {

    private val viewBinding: ActivityReviewBinding by viewBinding(R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movie: Movie = intent.extras!!.getParcelable<Movie>(MOVIE_ARG)!!

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, ReviewsListFragment.newInstance(movie))
            .commit()

        viewBinding.toolbar.apply {
            title = movie.title
            setNavigationOnClickListener { onBackPressed() }
        }
    }

    companion object {

        fun createReviewsIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, ReviewsActivity::class.java)
            intent.putExtra(MOVIE_ARG, movie)
            return intent
        }

        private const val MOVIE_ARG = "MOVIE_ARG"
    }
}
