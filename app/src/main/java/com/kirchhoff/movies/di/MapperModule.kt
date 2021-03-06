package com.kirchhoff.movies.di

import com.kirchhoff.movies.mapper.core.CoreMapper
import com.kirchhoff.movies.mapper.core.ICoreMapper
import com.kirchhoff.movies.mapper.discover.DiscoverMapper
import com.kirchhoff.movies.mapper.discover.IDiscoverMapper
import com.kirchhoff.movies.mapper.movie.IMovieDetailsMapper
import com.kirchhoff.movies.mapper.movie.MovieDetailsMapper
import com.kirchhoff.movies.mapper.person.details.IPersonDetailsMapper
import com.kirchhoff.movies.mapper.person.details.PersonDetailsMapper
import com.kirchhoff.movies.mapper.person.main.IPersonsMapper
import com.kirchhoff.movies.mapper.person.main.PersonsMapper
import com.kirchhoff.movies.mapper.review.IReviewListMapper
import com.kirchhoff.movies.mapper.review.ReviewListMapper
import com.kirchhoff.movies.mapper.tv.ITvDetailsMapper
import com.kirchhoff.movies.mapper.tv.TvDetailsMapper
import org.koin.dsl.module

val mapperModule = module {
    single<ICoreMapper> { CoreMapper() }
    single<IPersonDetailsMapper> { PersonDetailsMapper() }
    single<IMovieDetailsMapper> { MovieDetailsMapper(coreMapper = get()) }
    single<ITvDetailsMapper> { TvDetailsMapper(coreMapper = get()) }
    single<IReviewListMapper> { ReviewListMapper() }
    single<IDiscoverMapper> { DiscoverMapper() }
    single<IPersonsMapper> { PersonsMapper() }
}
