package com.kirchhoff.movies.screen.tvshow

import com.kirchhoff.movies.screen.tvshow.mapper.details.ITvShowDetailsMapper
import com.kirchhoff.movies.screen.tvshow.mapper.details.TvShowDetailsMapper
import com.kirchhoff.movies.screen.tvshow.mapper.list.ITvShowListMapper
import com.kirchhoff.movies.screen.tvshow.mapper.list.TvShowListMapper
import com.kirchhoff.movies.screen.tvshow.network.TvShowService
import com.kirchhoff.movies.screen.tvshow.repository.ITvShowRepository
import com.kirchhoff.movies.screen.tvshow.repository.TvShowRepository
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.viewmodel.TvShowListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

internal val tvShowModule = module {
    single { get<Retrofit>().create(TvShowService::class.java) }

    single<ITvShowListMapper> { TvShowListMapper() }

    single<ITvShowDetailsMapper> { TvShowDetailsMapper(coreMapper = get()) }

    single<ITvShowRepository> {
        TvShowRepository(
            tvService = get(),
            tvDetailsMapper = get(),
            tvShowListMapper = get(),
            tvShowStorage = get()
        )
    }

    viewModel { TvShowListViewModel(tvShowRepository = get()) }
}
