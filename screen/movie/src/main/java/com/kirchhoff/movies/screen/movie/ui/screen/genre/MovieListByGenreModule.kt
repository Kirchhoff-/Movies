package com.kirchhoff.movies.screen.movie.ui.screen.genre

import com.kirchhoff.movies.core.data.UIGenre
import com.kirchhoff.movies.screen.movie.ui.screen.genre.viewmodel.MovieListByGenreViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieListByGenreModule = module {
    viewModel { (genre: UIGenre) ->
        MovieListByGenreViewModel(
            genre = genre,
            movieRepository = get()
        )
    }
}
