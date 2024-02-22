package com.kirchhoff.movies.storage.tvshow

import org.koin.dsl.module

val storageTvShowModule = module {
    single<IStorageTvShow> { StorageTvShow() }
}
