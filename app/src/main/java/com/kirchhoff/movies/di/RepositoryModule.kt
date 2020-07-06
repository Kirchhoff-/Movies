package com.kirchhoff.movies.di

import com.kirchhoff.movies.repository.discover.DiscoverRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<DiscoverRepository> { DiscoverRepository(discoverService = get()) }
}