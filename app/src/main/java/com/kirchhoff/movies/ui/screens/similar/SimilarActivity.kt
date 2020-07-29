package com.kirchhoff.movies.ui.screens.similar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.Movie
import com.kirchhoff.movies.data.Tv
import com.kirchhoff.movies.databinding.ActivitySimilarBinding
import com.kirchhoff.movies.ui.screens.similar.movie.SimilarMoviesFragment
import com.kirchhoff.movies.ui.screens.similar.tv.SimilarTvsFragment
import com.kirchhoff.movies.utils.binding.viewBinding

class SimilarActivity : AppCompatActivity(R.layout.activity_similar) {

    private val viewBinding: ActivitySimilarBinding by viewBinding(R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val similarType: SimilarType =
            SimilarType.values()[intent.extras!!.getInt(SIMILAR_TYPE_ARG)]

        when (similarType) {
            SimilarType.MOVIE -> similarMovie()
            SimilarType.TV -> similarTv()
        }

        viewBinding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun similarMovie() {
        val movie: Movie = intent.getParcelableExtra(MOVIE_ARG)!!

        viewBinding.toolbar.title = getString(R.string.movies_like, movie.title)
        openSimilar(SimilarMoviesFragment.newInstance(movie.id))
    }

    private fun similarTv() {
        val tv: Tv = intent.getParcelableExtra(TV_ARG)!!

        viewBinding.toolbar.title = getString(R.string.tv_show_like, tv.name)
        openSimilar(SimilarTvsFragment.newInstance(tv.id))
    }

    private fun openSimilar(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    companion object {

        fun createSimilarMoviesIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, SimilarActivity::class.java)
            intent.putExtra(SIMILAR_TYPE_ARG, SimilarType.MOVIE.ordinal)
            intent.putExtra(MOVIE_ARG, movie)
            return intent
        }

        fun createSimilarTvIntent(context: Context, tv: Tv): Intent {
            val intent = Intent(context, SimilarActivity::class.java)
            intent.putExtra(SIMILAR_TYPE_ARG, SimilarType.TV.ordinal)
            intent.putExtra(TV_ARG, tv)
            return intent
        }

        private enum class SimilarType {
            MOVIE, TV
        }

        private const val SIMILAR_TYPE_ARG = "SIMILAR_TYPE_ARG"
        private const val MOVIE_ARG = "MOVIE_ARG"
        private const val TV_ARG = "TV_ARG"
    }
}