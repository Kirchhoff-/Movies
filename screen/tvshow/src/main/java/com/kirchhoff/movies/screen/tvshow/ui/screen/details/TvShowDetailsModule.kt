package com.kirchhoff.movies.screen.tvshow.ui.screen.details

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.screen.tvshow.router.ITvShowRouter
import com.kirchhoff.movies.screen.tvshow.router.TvShowRouter
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.mapper.ITvShowDetailsMapper
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.mapper.TvShowDetailsMapper
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.network.TvShowDetailsService
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.repository.ITvShowDetailsRepository
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.repository.TvShowDetailsRepository
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.usecase.ITvShowDetailsUseCase
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.usecase.TvShowDetailsUseCase
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.viewmodel.TvShowDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

internal val tvShowDetailsModule = module {
    factory<ITvShowRouter> { (activity: AppCompatActivity) ->
        TvShowRouter(activity)
    }

    single { get<Retrofit>().create(TvShowDetailsService::class.java) }

    single<ITvShowDetailsRepository> {
        TvShowDetailsRepository(tvShowDetailsService = get())
    }

    single<ITvShowDetailsMapper> {
        TvShowDetailsMapper(coreMapper = get())
    }

    single<ITvShowDetailsUseCase> {
        TvShowDetailsUseCase(
            tvShowDetailsRepository = get(),
            tvShowDetailsMapper = get(),
            coreMapper = get()
        )
    }

    viewModel { (tvShow: UITv) ->
        TvShowDetailsViewModel(
            tvShow = tvShow,
            tvShowDetailsUseCase = get()
        )
    }
}
