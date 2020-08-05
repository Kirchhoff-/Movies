package com.kirchhoff.movies.di

import com.kirchhoff.movies.mapper.movie.IMovieDetailsMapper
import com.kirchhoff.movies.mapper.movie.MovieDetailsMapper
import com.kirchhoff.movies.mapper.person.IPersonDetailsMapper
import com.kirchhoff.movies.mapper.person.PersonDetailsMapper
import org.koin.dsl.module

val mapperModule = module {
    single<IPersonDetailsMapper> { PersonDetailsMapper() }
    single<IMovieDetailsMapper> { MovieDetailsMapper() }
}
