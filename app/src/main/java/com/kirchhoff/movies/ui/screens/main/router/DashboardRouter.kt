package com.kirchhoff.movies.ui.screens.main.router

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kirchhoff.movies.core.R
import com.kirchhoff.movies.screen.movie.ui.screen.discover.MovieListDiscoverFragment
import com.kirchhoff.movies.screen.person.ui.screen.list.PersonListFragment
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.TvShowListFragment
import com.kirchhoff.movies.ui.screens.main.MainFragment

class DashboardRouter(private val activity: AppCompatActivity) : IDashboardRouter {

    override fun openDashboard() {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, MainFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun createScreenForDashboard(position: Int): Fragment = when (position) {
        0 -> MovieListDiscoverFragment()
        1 -> TvShowListFragment()
        else -> PersonListFragment()
    }
}
