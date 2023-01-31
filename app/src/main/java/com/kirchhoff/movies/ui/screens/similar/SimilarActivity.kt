package com.kirchhoff.movies.ui.screens.similar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.ui.main.UIMovie
import com.kirchhoff.movies.data.ui.main.UITv
import com.kirchhoff.movies.databinding.ActivitySimilarBinding
import com.kirchhoff.movies.ui.screens.similar.movie.SimilarMoviesFragment
import com.kirchhoff.movies.ui.screens.similar.tv.SimilarTvsFragment
import com.kirchhoff.movies.utils.viewBinding

class SimilarActivity : AppCompatActivity() {

    private val viewBinding by viewBinding(ActivitySimilarBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        val similarType: SimilarType =
            SimilarType.values()[intent.extras!!.getInt(SIMILAR_TYPE_ARG)]

        when (similarType) {
            SimilarType.MOVIE -> similarMovie()
            SimilarType.TV -> similarTv()
        }

        viewBinding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun similarMovie() {
        val movie: UIMovie = intent.getParcelableExtra(MOVIE_ARG)!!

        viewBinding.toolbar.title = getString(R.string.movies_like, movie.title)
        openSimilar(SimilarMoviesFragment.newInstance(movie.id))
    }

    private fun similarTv() {
        val tv: UITv = intent.getParcelableExtra(TV_ARG)!!

        viewBinding.toolbar.title = getString(R.string.tv_show_like, tv.name)
        openSimilar(SimilarTvsFragment.newInstance(tv.id))
    }

    private fun openSimilar(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    companion object {

        fun createSimilarMoviesIntent(context: Context, movie: UIMovie): Intent {
            val intent = Intent(context, SimilarActivity::class.java)
            intent.putExtra(SIMILAR_TYPE_ARG, SimilarType.MOVIE.ordinal)
            intent.putExtra(MOVIE_ARG, movie)
            return intent
        }

        fun createSimilarTvIntent(context: Context, tv: UITv): Intent {
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
