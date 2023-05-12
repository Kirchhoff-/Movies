package com.kirchhoff.movies.di

import com.kirchhoff.movies.screen.movie.repository.IMovieRepository
import com.kirchhoff.movies.screen.movie.repository.MovieRepository
import com.kirchhoff.movies.screen.person.repository.IPersonsRepository
import com.kirchhoff.movies.screen.person.repository.PersonsRepository
import com.kirchhoff.movies.screen.tvshow.repository.ITvShowRepository
import com.kirchhoff.movies.screen.tvshow.repository.TvShowRepository
import org.koin.dsl.module

val repositoryModule = module {
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
    single<ITvShowRepository> {
        TvShowRepository(
            tvService = get(),
            tvDetailsMapper = get(),
            discoverMapper = get()
        )
    }
}
