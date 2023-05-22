package com.kirchhoff.movies.di

import com.kirchhoff.movies.screen.tvshow.repository.ITvShowRepository
import com.kirchhoff.movies.screen.tvshow.repository.TvShowRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ITvShowRepository> {
        TvShowRepository(
            tvService = get(),
            tvDetailsMapper = get(),
            discoverMapper = get()
        )
    }
}
