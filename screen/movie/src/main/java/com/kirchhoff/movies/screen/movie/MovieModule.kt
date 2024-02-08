package com.kirchhoff.movies.screen.movie

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.screen.movie.mapper.details.IMovieDetailsMapper
import com.kirchhoff.movies.screen.movie.mapper.details.MovieDetailsMapper
import com.kirchhoff.movies.screen.movie.network.MovieService
import com.kirchhoff.movies.screen.movie.repository.IMovieRepository
import com.kirchhoff.movies.screen.movie.repository.MovieRepository
import com.kirchhoff.movies.screen.movie.router.IMovieRouter
import com.kirchhoff.movies.screen.movie.router.MovieRouter
import com.kirchhoff.movies.screen.movie.storage.IMovieImagesStorage
import com.kirchhoff.movies.screen.movie.storage.MovieImagesStorage
import org.koin.dsl.module
import retrofit2.Retrofit

internal val movieModule = module {
    single { get<Retrofit>().create(MovieService::class.java) }

    single<IMovieRouter> { (activity: AppCompatActivity) ->
        MovieRouter(activity)
    }

    single<IMovieDetailsMapper> { MovieDetailsMapper(coreMapper = get()) }

    single<IMovieImagesStorage> { MovieImagesStorage() }

    single<IMovieRepository> {
        MovieRepository(
            movieService = get(),
            movieStorage = get(),
            movieImagesStorage = get(),
            movieDetailsMapper = get(),
            discoverMapper = get()
        )
    }
}
