package com.kirchhoff.movies.router

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.core.router.IRouter
import org.koin.dsl.module

val routerModule = module {
    single<IRouter> { (activity: AppCompatActivity) ->
        Router(
            activity = activity,
            movieFacade = get(),
            tvShowFacade = get(),
            personFacade = get(),
            reviewFacade = get(),
            creditsFacade = get()
        )
    }
}
