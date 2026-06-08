package com.kirchhoff.movies.storage.tvshow

import com.kirchhoff.movies.networkdata.main.NetworkTv

class StorageTvShow {
    private val tvShowCache: MutableMap<Int, NetworkTv> = HashMap()

    fun updateInfo(tvShow: NetworkTv) {
        tvShowCache[tvShow.id] = tvShow
    }

    fun info(tvShowId: Int): NetworkTv? = tvShowCache[tvShowId]
}
