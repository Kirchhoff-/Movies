package com.kirchhoff.movies.screen.tvshow

import com.kirchhoff.movies.screen.tvshow.mapper.details.ITvShowDetailsMapper
import com.kirchhoff.movies.screen.tvshow.mapper.details.TvShowDetailsMapper
import com.kirchhoff.movies.screen.tvshow.network.TvShowService
import com.kirchhoff.movies.screen.tvshow.repository.ITvShowRepository
import com.kirchhoff.movies.screen.tvshow.repository.TvShowRepository
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.TvShowListVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val tvShowModule = module {
    single { get<Retrofit>().create(TvShowService::class.java) }

    single<ITvShowDetailsMapper> { TvShowDetailsMapper(coreMapper = get()) }

    single<ITvShowRepository> {
        TvShowRepository(
            tvService = get(),
            tvDetailsMapper = get(),
            discoverMapper = get()
        )
    }

    viewModel { TvShowListVM(discoverRepository = get()) }
}
