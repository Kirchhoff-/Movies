package com.kirchhoff.movies.screen.tvshow.router

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.core.R
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.screen.tvshow.ui.screen.similar.TvShowSimilarFragment

internal interface ITvShowRouter {
    fun openSimilarTvShowScreen(tv: UITv)
}

internal class TvShowRouter(private val activity: AppCompatActivity) : ITvShowRouter {

    override fun openSimilarTvShowScreen(tv: UITv) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, TvShowSimilarFragment.newInstance(tv))
            .addToBackStack(null)
            .commit()
    }
}
