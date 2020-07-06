package com.kirchhoff.movies.di

import com.kirchhoff.movies.repository.discover.DiscoverRepository
import com.kirchhoff.movies.repository.discover.IDiscoverRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IDiscoverRepository> { DiscoverRepository(discoverService = get()) }
}