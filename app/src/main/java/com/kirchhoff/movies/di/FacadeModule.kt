package com.kirchhoff.movies.di

import com.kirchhoff.movies.screen.credits.CreditsFacade
import com.kirchhoff.movies.screen.credits.ICreditsFacade
import com.kirchhoff.movies.screen.movie.IMovieFacade
import com.kirchhoff.movies.screen.movie.MovieFacade
import com.kirchhoff.movies.screen.person.IPersonFacade
import com.kirchhoff.movies.screen.person.PersonFacade
import com.kirchhoff.movies.screen.review.IReviewFacade
import com.kirchhoff.movies.screen.review.ReviewFacade
import com.kirchhoff.movies.screen.tvshow.ITvShowFacade
import com.kirchhoff.movies.screen.tvshow.TvShowFacade
import org.koin.dsl.module

val facadeModule = module {
    single<IMovieFacade> { MovieFacade() }
    single<ITvShowFacade> { TvShowFacade() }
    single<IReviewFacade> { ReviewFacade() }
    single<IPersonFacade> { PersonFacade() }
    single<ICreditsFacade> { CreditsFacade() }
}
