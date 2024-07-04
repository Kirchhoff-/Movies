package com.kirchhoff.movies.screen.tvshow.ui.screen.list

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.screen.tvshow.router.ITvShowRouter
import com.kirchhoff.movies.screen.tvshow.router.TvShowRouter
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.mapper.ITvShowListMapper
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.mapper.TvShowListMapper
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.network.TvShowListService
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.repository.ITvShowListRepository
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.repository.TvShowListRepository
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.usecase.ITvShowListUseCase
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.usecase.TvShowListUseCase
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.viewmodel.TvShowListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

internal fun tvShowListModule(tvShowListType: TvShowListType): Module = module {
    factory<ITvShowRouter> { (activity: AppCompatActivity) ->
        TvShowRouter(activity)
    }

    single { get<Retrofit>().create(TvShowListService::class.java) }

    single<ITvShowListRepository> { TvShowListRepository(tvShowListService = get()) }

    single<ITvShowListMapper> { TvShowListMapper() }

    single<ITvShowListUseCase> {
        TvShowListUseCase(
            tvShowListType = tvShowListType,
            tvShowListRepository = get(),
            tvShowListMapper = get()
        )
    }

    viewModel { TvShowListViewModel(tvShowListUseCase = get()) }
}
