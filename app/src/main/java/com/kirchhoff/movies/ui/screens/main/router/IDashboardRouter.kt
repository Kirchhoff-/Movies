package com.kirchhoff.movies.ui.screens.main.router

import androidx.fragment.app.Fragment

interface IDashboardRouter {
    fun openDashboard()
    fun createScreenForDashboard(position: Int): Fragment
}
