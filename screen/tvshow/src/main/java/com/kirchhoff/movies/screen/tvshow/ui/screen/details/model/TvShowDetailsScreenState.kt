package com.kirchhoff.movies.screen.tvshow.ui.screen.details.model

import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.tvshow.data.UITvShowDetails

internal data class TvShowDetailsScreenState(
    val title: StringValue,
    val details: UITvShowDetails,
    val credits: UIEntertainmentCredits,
    val isLoading: Boolean,
    val errorMessage: StringValue
)
