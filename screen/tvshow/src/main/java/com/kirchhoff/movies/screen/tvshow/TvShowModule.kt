package com.kirchhoff.movies.screen.tvshow

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.screen.tvshow.mapper.ITvShowDetailsMapper
import com.kirchhoff.movies.screen.tvshow.mapper.ITvShowListMapper
import com.kirchhoff.movies.screen.tvshow.mapper.TvShowDetailsMapper
import com.kirchhoff.movies.screen.tvshow.mapper.TvShowListMapper
import com.kirchhoff.movies.screen.tvshow.network.TvShowService
import com.kirchhoff.movies.screen.tvshow.repository.ITvShowRepository
import com.kirchhoff.movies.screen.tvshow.repository.TvShowRepository
import com.kirchhoff.movies.screen.tvshow.router.ITvShowRouter
import com.kirchhoff.movies.screen.tvshow.router.TvShowRouter
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.viewmodel.TvShowListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

internal val tvShowModule = module {
    single { get<Retrofit>().create(TvShowService::class.java) }

    single<ITvShowRouter> { (activity: AppCompatActivity) ->
        TvShowRouter(activity)
    }

    single<ITvShowListMapper> { TvShowListMapper() }

    single<ITvShowDetailsMapper> { TvShowDetailsMapper(coreMapper = get()) }

    single<ITvShowRepository> {
        TvShowRepository(
            tvService = get(),
            tvShowListMapper = get(),
            tvShowStorage = get()
        )
    }

    viewModel { TvShowListViewModel(tvShowRepository = get()) }
}
