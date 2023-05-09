package com.kirchhoff.movies.di

import com.kirchhoff.movies.core.mapper.DiscoverMapper
import com.kirchhoff.movies.core.mapper.IDiscoverMapper
import com.kirchhoff.movies.core.mapper.core.CoreMapper
import com.kirchhoff.movies.core.mapper.core.ICoreMapper
import com.kirchhoff.movies.mapper.movie.IMovieDetailsMapper
import com.kirchhoff.movies.mapper.movie.MovieDetailsMapper
import com.kirchhoff.movies.mapper.tv.ITvDetailsMapper
import com.kirchhoff.movies.mapper.tv.TvDetailsMapper
import com.kirchhoff.movies.screen.person.mapper.details.IPersonDetailsMapper
import com.kirchhoff.movies.screen.person.mapper.details.PersonDetailsMapper
import com.kirchhoff.movies.screen.person.mapper.main.IPersonsMapper
import com.kirchhoff.movies.screen.person.mapper.main.PersonsMapper
import org.koin.dsl.module

val mapperModule = module {
    single<ICoreMapper> { CoreMapper() }
    single<IPersonDetailsMapper> { PersonDetailsMapper() }
    single<IMovieDetailsMapper> { MovieDetailsMapper(coreMapper = get()) }
    single<ITvDetailsMapper> { TvDetailsMapper(coreMapper = get()) }
    single<IDiscoverMapper> { DiscoverMapper() }
    single<IPersonsMapper> { PersonsMapper() }
}
