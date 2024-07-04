package com.kirchhoff.movies.screen.tvshow.router

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.core.data.TvId
import com.kirchhoff.movies.core.extensions.replaceFragment
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
        activity.replaceFragment(TvShowListFragment.similar(tvId))
    }

    override fun openAiringTodayScreen() {
        activity.replaceFragment(TvShowListFragment.airingToday())
    }

    override fun openOnTheAirScreen() {
        activity.replaceFragment(TvShowListFragment.onTheAir())
    }

    override fun openPopularScreen() {
        activity.replaceFragment(TvShowListFragment.popular())
    }

    override fun openTopRatedScreen() {
        activity.replaceFragment(TvShowListFragment.topRated())
    }
}
