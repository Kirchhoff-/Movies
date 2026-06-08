package com.kirchhoff.movies.screen.movie

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.screen.movie.mapper.MovieDetailsMapper
import com.kirchhoff.movies.screen.movie.mapper.MovieListMapper
import com.kirchhoff.movies.screen.movie.network.MovieService
import com.kirchhoff.movies.screen.movie.repository.MovieRepository
import com.kirchhoff.movies.screen.movie.router.MovieRouter
import com.kirchhoff.movies.screen.movie.storage.MovieImagesStorage
import com.kirchhoff.movies.screen.movie.usecase.MovieUseCase
import org.koin.dsl.module
import retrofit2.Retrofit

internal val movieModule = module {
    single { get<Retrofit>().create(MovieService::class.java) }

    single<MovieRouter> { (activity: AppCompatActivity) ->
        MovieRouter(activity)
    }

    single<MovieDetailsMapper> { MovieDetailsMapper(coreMapper = get()) }

    single<MovieListMapper> { MovieListMapper() }

    single<MovieImagesStorage> { MovieImagesStorage() }

    single<MovieRepository> {
        MovieRepository(
            movieService = get(),
            movieStorage = get(),
            movieImagesStorage = get(),
            movieDetailsMapper = get()
        )
    }

    single<MovieUseCase> {
        MovieUseCase(movieRepository = get())
    }
}
