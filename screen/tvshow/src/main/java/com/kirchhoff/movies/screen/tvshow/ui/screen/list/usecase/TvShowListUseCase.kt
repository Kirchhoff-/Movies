package com.kirchhoff.movies.screen.tvshow.ui.screen.list.usecase

import com.kirchhoff.movies.core.data.ui.UITv
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.tvshow.R
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.TvShowListType
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.mapper.ITvShowListMapper
import com.kirchhoff.movies.screen.tvshow.ui.screen.list.repository.ITvShowListRepository

internal interface ITvShowListUseCase {
    suspend fun load(page: Int): kotlin.Result<UIPaginated<UITv>>
    fun title(): StringValue
}

internal class TvShowListUseCase(
    private val tvShowListType: TvShowListType,
    private val tvShowListRepository: ITvShowListRepository,
    private val tvShowListMapper: ITvShowListMapper
) : ITvShowListUseCase {

    override suspend fun load(page: Int): kotlin.Result<UIPaginated<UITv>> {
        val result = when (tvShowListType) {
            is TvShowListType.Similar -> tvShowListRepository.similar(tvShowListType.id, page)
            is TvShowListType.AiringToday -> tvShowListRepository.airingToday(page)
            is TvShowListType.OnTheAir -> tvShowListRepository.onTheAir(page)
            is TvShowListType.Popular -> tvShowListRepository.popular(page)
            is TvShowListType.TopRated -> tvShowListRepository.topRated(page)
        }

        return if (result is Result.Success) {
            kotlin.Result.success(tvShowListMapper.createTvShowList(result.data))
        } else {
            kotlin.Result.failure(Exception("Can't load list"))
        }
    }

    override fun title(): StringValue = when (tvShowListType) {
        is TvShowListType.Similar -> StringValue.IdText(R.string.similar_tv_shows)
        is TvShowListType.AiringToday -> StringValue.IdText(R.string.tv_show_airing_today)
        is TvShowListType.OnTheAir -> StringValue.IdText(R.string.tv_show_on_the_air)
        is TvShowListType.Popular -> StringValue.IdText(R.string.tv_show_popular)
        is TvShowListType.TopRated -> StringValue.IdText(R.string.tv_show_top_rated)
    }
}
