package com.kirchhoff.movies.di

import com.kirchhoff.movies.core.mapper.CoreMapper
import org.koin.dsl.module

val mapperModule = module {
    single<CoreMapper> { CoreMapper() }
}
