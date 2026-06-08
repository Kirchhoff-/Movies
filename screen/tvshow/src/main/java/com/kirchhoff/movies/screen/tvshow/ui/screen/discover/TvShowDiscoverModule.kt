package com.kirchhoff.movies.screen.tvshow.ui.screen.discover

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.screen.tvshow.router.TvShowRouter
import com.kirchhoff.movies.screen.tvshow.ui.screen.discover.mapper.TvShowDiscoverMapper
import com.kirchhoff.movies.screen.tvshow.ui.screen.discover.network.TvShowDiscoverService
import com.kirchhoff.movies.screen.tvshow.ui.screen.discover.repository.TvShowDiscoverRepository
import com.kirchhoff.movies.screen.tvshow.ui.screen.discover.usecase.TvShowDiscoverUseCase
import com.kirchhoff.movies.screen.tvshow.ui.screen.discover.viewmodel.TvShowDiscoverViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

internal val tvShowDiscoverModule = module {
    factory<TvShowRouter> { (activity: AppCompatActivity) ->
        TvShowRouter(activity)
    }

    single { get<Retrofit>().create(TvShowDiscoverService::class.java) }

    single<TvShowDiscoverMapper> { TvShowDiscoverMapper() }

    single<TvShowDiscoverRepository> {
        TvShowDiscoverRepository(
            tvShowDiscoverService = get(),
            tvShowStorage = get()
        )
    }

    single<TvShowDiscoverUseCase> {
        TvShowDiscoverUseCase(
            tvShowDiscoverRepository = get(),
            tvShowDiscoverMapper = get()
        )
    }

    viewModel { TvShowDiscoverViewModel(tvShowDiscoverUseCase = get()) }
}
