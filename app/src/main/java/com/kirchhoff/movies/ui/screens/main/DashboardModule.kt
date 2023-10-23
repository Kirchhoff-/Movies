package com.kirchhoff.movies.ui.screens.main

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.ui.screens.main.router.DashboardRouter
import com.kirchhoff.movies.ui.screens.main.router.IDashboardRouter
import org.koin.dsl.module

val dashboardModule = module {
    single<IDashboardRouter> { (activity: AppCompatActivity) ->
        DashboardRouter(
            activity,
            movieFacade = get(),
            tvShowFacade = get(),
            personFacade = get()
        )
    }
}
