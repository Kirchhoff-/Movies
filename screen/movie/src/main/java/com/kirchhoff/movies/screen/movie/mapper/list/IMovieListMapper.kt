package com.kirchhoff.movies.screen.movie.mapper.list

import com.kirchhoff.movies.core.data.UIMovie
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.core.ui.paginated.UIPaginated
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkMovie

internal interface IMovieListMapper {
    fun createMovieList(movies: Result<NetworkPaginated<NetworkMovie>>): Result<UIPaginated<UIMovie>>
}
