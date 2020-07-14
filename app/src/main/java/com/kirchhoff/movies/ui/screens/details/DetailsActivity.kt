package com.kirchhoff.movies.ui.screens.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.Movie
import com.kirchhoff.movies.ui.screens.details.movie.MovieDetailsFragment

class DetailsActivity : AppCompatActivity(R.layout.activity_details) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer, MovieDetailsFragment.newInstance(
                    intent.getParcelableExtra(
                        MOVIE_ARG
                    )!!
                )
            )
            .commit()
    }

    companion object {

        fun createMovieDetailsIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(MOVIE_ARG, movie)
            return intent
        }

        private const val MOVIE_ARG = "MOVIE_ARG"
    }
}