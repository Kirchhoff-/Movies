package com.kirchhoff.movies.ui.screens.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kirchhoff.movies.ui.screens.main.router.IDashboardRouter

internal class MainPagerAdapter(
    fm: FragmentManager,
    private val dashboardRouter: IDashboardRouter
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
        dashboardRouter.createScreenForDashboard(position)

    override fun getCount(): Int = 3
}
