package com.kirchhoff.movies.ui.screens.main.router

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kirchhoff.movies.core.R
import com.kirchhoff.movies.screen.movie.IMovieFacade
import com.kirchhoff.movies.screen.person.IPersonFacade
import com.kirchhoff.movies.screen.tvshow.ITvShowFacade
import com.kirchhoff.movies.ui.screens.main.MainFragment

class DashboardRouter(
    private val activity: AppCompatActivity,
    private val movieFacade: IMovieFacade,
    private val tvShowFacade: ITvShowFacade,
    private val personFacade: IPersonFacade
) : IDashboardRouter {

    override fun openDashboard() {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, MainFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun createScreenForDashboard(position: Int): Fragment = when (position) {
        0 -> movieFacade.movieList()
        1 -> tvShowFacade.tvShowList()
        else -> personFacade.personList()
    }
}
