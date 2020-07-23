package com.kirchhoff.movies.ui.screens.similar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.Movie
import com.kirchhoff.movies.databinding.ActivitySimilarBinding
import com.kirchhoff.movies.ui.screens.similar.movie.SimilarMoviesFragment
import com.kirchhoff.movies.utils.binding.viewBinding

class SimilarActivity : AppCompatActivity(R.layout.activity_similar) {

    private val viewBinding: ActivitySimilarBinding by viewBinding(R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movie: Movie = intent.extras!!.getParcelable<Movie>(MOVIE_ARG)!!

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, SimilarMoviesFragment.newInstance(movie.id))
            .commit()

        viewBinding.toolbar.apply {
            title = getString(R.string.movies_like, movie.title)
            setNavigationOnClickListener { onBackPressed() }
        }
    }

    companion object {

        fun createSimilarMoviesIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, SimilarActivity::class.java)
            intent.putExtra(MOVIE_ARG, movie)
            return intent
        }

        private const val MOVIE_ARG = "MOVIE_ARG"
    }
}