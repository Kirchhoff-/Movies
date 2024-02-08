package com.kirchhoff.movies.screen.tvshow.ui.screen.similar

import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.screen.tvshow.ui.screen.similar.viewmodel.TvShowSimilarViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val tvShowSimilarModule = module {
    viewModel { (tvShow: UITv) ->
        TvShowSimilarViewModel(
            tvShow = tvShow,
            tvShowRepository = get()
        )
    }
}
