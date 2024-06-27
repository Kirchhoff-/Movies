package com.kirchhoff.movies.screen.tvshow.ui.screen.list

import android.os.Parcelable
import com.kirchhoff.movies.core.data.TvId
import kotlinx.parcelize.Parcelize

internal sealed interface TvShowListType : Parcelable {

    @Parcelize
    data class Similar(val id: TvId) : TvShowListType, Parcelable

    @Parcelize
    object AiringToday : TvShowListType, Parcelable

    @Parcelize
    object OnTheAir : TvShowListType, Parcelable

    @Parcelize
    object Popular : TvShowListType, Parcelable

    @Parcelize
    object TopRated : TvShowListType, Parcelable
}
