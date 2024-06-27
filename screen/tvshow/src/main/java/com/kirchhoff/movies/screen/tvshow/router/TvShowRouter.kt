package com.kirchhoff.movies.screen.tvshow.router

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kirchhoff.movies.core.R
import com.kirchhoff.movies.core.data.TvId
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.TvShowListFragment

internal interface ITvShowRouter {
    fun openSimilarTvShowScreen(tvId: TvId)
    fun openAiringTodayScreen()
    fun openOnTheAirScreen()
    fun openPopularScreen()
    fun openTopRatedScreen()
}

internal class TvShowRouter(private val activity: AppCompatActivity) : ITvShowRouter {

    override fun openSimilarTvShowScreen(tvId: TvId) {
        replaceFragment(TvShowListFragment.similar(tvId))
    }

    override fun openAiringTodayScreen() {
        replaceFragment(TvShowListFragment.airingToday())
    }

    override fun openOnTheAirScreen() {
        replaceFragment(TvShowListFragment.onTheAir())
    }

    override fun openPopularScreen() {
        replaceFragment(TvShowListFragment.popular())
    }

    override fun openTopRatedScreen() {
        replaceFragment(TvShowListFragment.topRated())
    }

    private fun replaceFragment(fragment: Fragment) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}
