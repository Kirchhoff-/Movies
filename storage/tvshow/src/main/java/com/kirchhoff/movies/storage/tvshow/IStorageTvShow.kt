package com.kirchhoff.movies.storage.tvshow

import com.kirchhoff.movies.networkdata.main.NetworkTv

interface IStorageTvShow {
    fun updateInfo(tvShow: NetworkTv)
    fun info(tvShowId: Int): NetworkTv?
}
