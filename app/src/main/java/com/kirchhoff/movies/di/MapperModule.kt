package com.kirchhoff.movies.di

import com.kirchhoff.movies.mapper.person.IPersonDetailsMapper
import com.kirchhoff.movies.mapper.person.PersonDetailsMapper
import org.koin.dsl.module

val mapperModule = module {
    single<IPersonDetailsMapper> { PersonDetailsMapper() }
}
