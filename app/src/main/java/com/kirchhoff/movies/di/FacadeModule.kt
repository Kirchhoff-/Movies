package com.kirchhoff.movies.di

import com.kirchhoff.movies.screen.credits.CreditsFacade
import com.kirchhoff.movies.screen.movie.MovieFacade
import com.kirchhoff.movies.screen.person.PersonFacade
import com.kirchhoff.movies.screen.review.ReviewFacade
import com.kirchhoff.movies.screen.tvshow.TvShowFacade
import org.koin.dsl.module

val facadeModule = module {
    single<MovieFacade> { MovieFacade() }
    single<TvShowFacade> { TvShowFacade() }
    single<ReviewFacade> { ReviewFacade() }
    single<PersonFacade> { PersonFacade() }
    single<CreditsFacade> { CreditsFacade() }
}
