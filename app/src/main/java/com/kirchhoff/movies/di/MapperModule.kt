package com.kirchhoff.movies.di

import com.kirchhoff.movies.mapper.mapper.IReviewListMapper
import com.kirchhoff.movies.mapper.mapper.ReviewListMapper
import com.kirchhoff.movies.mapper.movie.IMovieDetailsMapper
import com.kirchhoff.movies.mapper.movie.MovieDetailsMapper
import com.kirchhoff.movies.mapper.person.IPersonDetailsMapper
import com.kirchhoff.movies.mapper.person.PersonDetailsMapper
import com.kirchhoff.movies.mapper.tv.ITvDetailsMapper
import com.kirchhoff.movies.mapper.tv.TvDetailsMapper
import org.koin.dsl.module

val mapperModule = module {
    single<IPersonDetailsMapper> { PersonDetailsMapper() }
    single<IMovieDetailsMapper> { MovieDetailsMapper() }
    single<ITvDetailsMapper> { TvDetailsMapper() }
    single<IReviewListMapper> { ReviewListMapper() }
}
