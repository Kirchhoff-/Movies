package com.kirchhoff.movies.storage.tvshow

import com.kirchhoff.movies.networkdata.main.NetworkTv

interface IStorageTvShow {
    fun updateInfo(tvShow: NetworkTv)
    fun info(tvShowId: Int): NetworkTv?
}

internal class StorageTvShow : IStorageTvShow {
    private val tvShowCache: MutableMap<Int, NetworkTv> = HashMap()

    override fun updateInfo(tvShow: NetworkTv) {
        tvShowCache[tvShow.id] = tvShow
    }

    override fun info(tvShowId: Int): NetworkTv? = tvShowCache[tvShowId]
}
