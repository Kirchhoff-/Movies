package com.kirchhoff.movies.screen.tvshow.ui.screen.details

import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.usecase.ITvShowDetailsUseCase
import com.kirchhoff.movies.screen.tvshow.ui.screen.details.usecase.TvShowDetailsUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val newTvShowDetailsModule = module {

    factory<ITvShowDetailsUseCase> {
        TvShowDetailsUseCase(
            tvShowRepository = get(),
            tvShowListMapper = get(),
            tvShowDetailsMapper = get()
        )
    }

    viewModel { (tvShow: UITv) ->
        TvShowDetailsViewModel(
            tvShow = tvShow,
            tvShowDetailsUseCase = get()
        )
    }
}
