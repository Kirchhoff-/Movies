package com.kirchhoff.movies.screen.similar

import com.kirchhoff.movies.screen.similar.network.SimilarService
import com.kirchhoff.movies.screen.similar.repository.ISimilarRepository
import com.kirchhoff.movies.screen.similar.repository.SimilarRepository
import com.kirchhoff.movies.screen.similar.ui.screen.movie.viewmodel.SimilarMoviesViewModel
import com.kirchhoff.movies.screen.similar.ui.screen.tv.viewmodel.viewmodel.SimilarTvsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val similarModule = module {
    single { get<Retrofit>().create(SimilarService::class.java) }

    single<ISimilarRepository> {
        SimilarRepository(
            similarService = get(),
            discoverMapper = get()
        )
    }

    viewModel { (movieId: Int) ->
        SimilarMoviesViewModel(
            movieId = movieId,
            similarRepository = get()
        )
    }

    viewModel { (tvId: Int) ->
        SimilarTvsViewModel(
            tvId = tvId,
            similarRepository = get()
        )
    }
}
