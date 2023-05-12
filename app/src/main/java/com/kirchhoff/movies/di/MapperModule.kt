package com.kirchhoff.movies.di

import com.kirchhoff.movies.core.mapper.DiscoverMapper
import com.kirchhoff.movies.core.mapper.IDiscoverMapper
import com.kirchhoff.movies.core.mapper.core.CoreMapper
import com.kirchhoff.movies.core.mapper.core.ICoreMapper
import com.kirchhoff.movies.screen.movie.mapper.details.IMovieDetailsMapper
import com.kirchhoff.movies.screen.movie.mapper.details.MovieDetailsMapper
import com.kirchhoff.movies.screen.person.mapper.details.IPersonDetailsMapper
import com.kirchhoff.movies.screen.person.mapper.details.PersonDetailsMapper
import com.kirchhoff.movies.screen.person.mapper.main.IPersonsMapper
import com.kirchhoff.movies.screen.person.mapper.main.PersonsMapper
import com.kirchhoff.movies.screen.tvshow.mapper.details.ITvShowDetailsMapper
import com.kirchhoff.movies.screen.tvshow.mapper.details.TvShowDetailsMapper
import org.koin.dsl.module

val mapperModule = module {
    single<ICoreMapper> { CoreMapper() }
    single<IPersonDetailsMapper> { PersonDetailsMapper() }
    single<IMovieDetailsMapper> { MovieDetailsMapper(coreMapper = get()) }
    single<ITvShowDetailsMapper> { TvShowDetailsMapper(coreMapper = get()) }
    single<IDiscoverMapper> { DiscoverMapper() }
    single<IPersonsMapper> { PersonsMapper() }
}
