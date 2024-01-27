package com.kirchhoff.movies.screen.tvshow.ui.screen.details

import com.kirchhoff.movies.core.data.UITv
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val newTvShowDetailsModule = module {
    viewModel { (tvShow: UITv) ->
        NewTvShowDetailsViewModel(
            tvShow = tvShow,
            tvRepository = get()
        )
    }
}
