package com.kirchhoff.movies.ui.screens.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.Person
import com.kirchhoff.movies.data.ui.main.UIMovie
import com.kirchhoff.movies.data.ui.main.UITv
import com.kirchhoff.movies.ui.screens.details.movie.MovieDetailsFragment
import com.kirchhoff.movies.ui.screens.details.person.PersonDetailsFragment
import com.kirchhoff.movies.ui.screens.details.tv.TvDetailsFragment

class DetailsActivity : AppCompatActivity(R.layout.activity_with_fragment) {

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
                TvDetailsFragment.newInstance(
                    intent.getParcelableExtra(
                        TV_ARG
                    )!!
                )
            )
            DetailsType.PERSON -> openDetails(
                PersonDetailsFragment.newInstance(
                    intent.getParcelableExtra(
                        PERSON_ARG
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

        fun createMovieDetailsIntent(context: Context, movie: UIMovie): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(DETAILS_TYPE_ARG, DetailsType.MOVIE.ordinal)
            intent.putExtra(MOVIE_ARG, movie)
            return intent
        }

        fun createTvDetailsIntent(context: Context, tv: UITv): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(DETAILS_TYPE_ARG, DetailsType.TV.ordinal)
            intent.putExtra(TV_ARG, tv)
            return intent
        }

        fun createPersonDetailsIntent(context: Context, person: Person): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(DETAILS_TYPE_ARG, DetailsType.PERSON.ordinal)
            intent.putExtra(PERSON_ARG, person)
            return intent
        }

        private enum class DetailsType {
            MOVIE, TV, PERSON
        }

        private const val DETAILS_TYPE_ARG = "DETAILS_TYPE_ARG"
        private const val MOVIE_ARG = "MOVIE_ARG"
        private const val TV_ARG = "TV_ARG"
        private const val PERSON_ARG = "PERSON_ARG"
    }
}
