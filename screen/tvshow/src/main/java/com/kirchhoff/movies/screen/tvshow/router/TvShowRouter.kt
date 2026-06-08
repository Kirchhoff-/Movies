package com.kirchhoff.movies.screen.tvshow.router

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.core.data.TvId
import com.kirchhoff.movies.core.extensions.replaceFragment
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.TvShowListFragment

internal class TvShowRouter(private val activity: AppCompatActivity) {

    fun openSimilarTvShowScreen(tvId: TvId) {
        activity.replaceFragment(TvShowListFragment.similar(tvId))
    }

    fun openAiringTodayScreen() {
        activity.replaceFragment(TvShowListFragment.airingToday())
    }

    fun openOnTheAirScreen() {
        activity.replaceFragment(TvShowListFragment.onTheAir())
    }

    fun openPopularScreen() {
        activity.replaceFragment(TvShowListFragment.popular())
    }

    fun openTopRatedScreen() {
        activity.replaceFragment(TvShowListFragment.topRated())
    }
}
