package com.kirchhoff.movies.storage.tvshow

import com.kirchhoff.movies.storage.tvshow.storage.StorageTvShow
import org.koin.dsl.module

val storageTvShowModule = module {
    single<IStorageTvShow> { StorageTvShow() }
}
