package com.kirchhoff.movies.screen.tvshow.ui.screen.similar

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val tvShowSimilarModule = module {
    viewModel { (tvId: Int) ->
        TvShowSimilarViewModel(
            tvId = tvId,
            tvShowRepository = get()
        )
    }
}
