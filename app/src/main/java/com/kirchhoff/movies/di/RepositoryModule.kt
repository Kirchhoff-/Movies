package com.kirchhoff.movies.di

import com.kirchhoff.movies.repository.discover.DiscoverRepository
import com.kirchhoff.movies.repository.discover.IDiscoverRepository
import com.kirchhoff.movies.repository.movie.IMovieRepository
import com.kirchhoff.movies.repository.movie.MovieRepository
import com.kirchhoff.movies.repository.tv.ITvRepository
import com.kirchhoff.movies.repository.tv.TvRepository
import com.kirchhoff.movies.screen.person.repository.IPersonsRepository
import com.kirchhoff.movies.screen.person.repository.PersonsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IDiscoverRepository> {
        DiscoverRepository(
            discoverService = get(),
            discoverMapper = get()
        )
    }
    single<IPersonsRepository> {
        PersonsRepository(
            personService = get(),
            personMapper = get(),
            personDetailsMapper = get()
        )
    }
    single<IMovieRepository> {
        MovieRepository(
            movieService = get(),
            movieDetailsMapper = get(),
            discoverMapper = get()
        )
    }
    single<ITvRepository> {
        TvRepository(
            tvService = get(),
            tvDetailsMapper = get(),
            discoverMapper = get()
        )
    }
}
