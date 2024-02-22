package com.kirchhoff.movies.di

import com.kirchhoff.movies.core.mapper.CoreMapper
import com.kirchhoff.movies.core.mapper.ICoreMapper
import org.koin.dsl.module

val mapperModule = module {
    single<ICoreMapper> { CoreMapper() }
}
