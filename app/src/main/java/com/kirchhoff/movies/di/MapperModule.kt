package com.kirchhoff.movies.di

import com.kirchhoff.movies.core.mapper.DiscoverMapper
import com.kirchhoff.movies.core.mapper.IDiscoverMapper
import com.kirchhoff.movies.core.mapper.core.CoreMapper
import com.kirchhoff.movies.core.mapper.core.ICoreMapper
import org.koin.dsl.module

val mapperModule = module {
    single<ICoreMapper> { CoreMapper() }
    single<IDiscoverMapper> { DiscoverMapper() }
}
