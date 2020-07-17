package com.kirchhoff.movies.ui.screens.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.Movie
import com.kirchhoff.movies.data.Tv
import com.kirchhoff.movies.ui.screens.details.movie.MovieDetailsFragment
import com.kirchhoff.movies.ui.screens.details.tv.TvShowDetailsFragment

class DetailsActivity : AppCompatActivity(R.layout.activity_details) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val detailsType: DetailsType =
            DetailsType.values()[intent.extras!!.getInt(DETAILS_TYPE_ARG)]

        when (detailsType) {
            DetailsType.MOVIE -> openDetails(
                MovieDetailsFragment.newInstance(
                    intent.getParcelableExtra(
                        MOVIE_ARG
                    )!!
                )
            )
            DetailsType.TV -> openDetails(
                TvShowDetailsFragment.newInstance(
                    intent.getParcelableExtra(
                        TV_ARG
                    )!!
                )
            )
        }
    }

    private fun openDetails(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    companion object {

        fun createMovieDetailsIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(DETAILS_TYPE_ARG, DetailsType.MOVIE.ordinal)
            intent.putExtra(MOVIE_ARG, movie)
            return intent
        }

        fun createTvDetailsIntent(context: Context, tv: Tv): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(DETAILS_TYPE_ARG, DetailsType.TV.ordinal)
            intent.putExtra(TV_ARG, tv)
            return intent
        }

        private enum class DetailsType {
            MOVIE, TV
        }

        private const val DETAILS_TYPE_ARG = "DETAILS_TYPE_ARG"
        private const val MOVIE_ARG = "MOVIE_ARG"
        private const val TV_ARG = "TV_ARG"
    }
}
