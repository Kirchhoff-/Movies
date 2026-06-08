package com.kirchhoff.movies.screen.tvshow.ui.screen.list

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.screen.tvshow.router.TvShowRouter
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.mapper.TvShowListMapper
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.network.TvShowListService
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.repository.TvShowListRepository
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.usecase.TvShowListUseCase
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.viewmodel.TvShowListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

internal fun tvShowListModule(tvShowListType: TvShowListType): Module = module {
    factory<TvShowRouter> { (activity: AppCompatActivity) ->
        TvShowRouter(activity)
    }

    single { get<Retrofit>().create(TvShowListService::class.java) }

    single<TvShowListRepository> { TvShowListRepository(tvShowListService = get()) }

    single<TvShowListMapper> { TvShowListMapper() }

    single<TvShowListUseCase> {
        TvShowListUseCase(
            tvShowListType = tvShowListType,
            tvShowListRepository = get(),
            tvShowListMapper = get()
        )
    }

    viewModel { TvShowListViewModel(tvShowListUseCase = get()) }
}
