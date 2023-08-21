package com.kirchhoff.movies.ui.screens.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kirchhoff.movies.screen.movie.ui.screen.list.MovieListFragment
import com.kirchhoff.movies.screen.person.ui.screen.list.PersonListFragment
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.TvShowListFragment

class MainPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MovieListFragment()
            1 -> TvShowListFragment()
            else -> PersonListFragment()
        }
    }

    override fun getCount() = 3
}
