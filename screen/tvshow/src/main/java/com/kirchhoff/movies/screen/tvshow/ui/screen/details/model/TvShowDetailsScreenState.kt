package com.kirchhoff.movies.screen.tvshow.ui.screen.details.model

import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UITv
import com.kirchhoff.movies.core.utils.StringValue

internal data class TvShowDetailsScreenState(
    val title: StringValue,
    val backdropPath: String?,
    val posterPath: String?,
    val info: TvShowDetailsInfo,
    val credits: UIEntertainmentCredits,
    val similarTvShows: List<UITv>,
    val isLoading: Boolean,
    val errorMessage: StringValue
) {
    companion object {
        val Default = TvShowDetailsScreenState(
            title = StringValue.Empty,
            backdropPath = "",
            posterPath = "",
            info = TvShowDetailsInfo.Default,
            credits = UIEntertainmentCredits.Default,
            similarTvShows = emptyList(),
            isLoading = false,
            errorMessage = StringValue.Empty
        )
    }
}
