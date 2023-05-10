package com.kirchhoff.movies.ui.screens.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kirchhoff.movies.screen.person.ui.screen.list.PersonsFragment
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.TvShowListFragment
import com.kirchhoff.movies.ui.screens.main.movies.MoviesFragment

class MainPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MoviesFragment()
            1 -> TvShowListFragment()
            else -> PersonsFragment()
        }
    }

    override fun getCount() = 3
}
