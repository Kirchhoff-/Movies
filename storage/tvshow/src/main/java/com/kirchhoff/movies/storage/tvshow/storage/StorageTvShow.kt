package com.kirchhoff.movies.storage.tvshow.storage

import com.kirchhoff.movies.networkdata.main.NetworkTv
import com.kirchhoff.movies.storage.tvshow.IStorageTvShow

internal class StorageTvShow : IStorageTvShow {
    private val tvShowCache: MutableMap<Int, NetworkTv> = HashMap()

    override fun updateInfo(tvShow: NetworkTv) {
        tvShowCache[tvShow.id] = tvShow
    }

    override fun info(tvShowId: Int): NetworkTv? = tvShowCache[tvShowId]
}
