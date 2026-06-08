package com.kirchhoff.movies.ui.screens.main.router

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kirchhoff.movies.core.R
import com.kirchhoff.movies.screen.movie.MovieFacade
import com.kirchhoff.movies.screen.person.PersonFacade
import com.kirchhoff.movies.screen.tvshow.TvShowFacade
import com.kirchhoff.movies.ui.screens.main.MainFragment

class DashboardRouter(
    private val activity: AppCompatActivity,
    private val movieFacade: MovieFacade,
    private val tvShowFacade: TvShowFacade,
    private val personFacade: PersonFacade
) {

    fun openDashboard() {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, MainFragment.newInstance())
            .commit()
    }

    fun createScreenForDashboard(position: Int): Fragment = when (position) {
        0 -> movieFacade.movieList()
        1 -> tvShowFacade.tvShowList()
        else -> personFacade.personList()
    }
}
