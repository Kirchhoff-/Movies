package com.kirchhoff.movies.mapper.movie

import com.kirchhoff.movies.data.network.details.movie.NetworkMovieCredits
import com.kirchhoff.movies.data.network.details.movie.NetworkMovieDetails
import com.kirchhoff.movies.data.network.details.movie.NetworkTrailersList
import com.kirchhoff.movies.data.ui.details.movie.UIMovieCredits
import com.kirchhoff.movies.data.ui.details.movie.UIMovieDetails
import com.kirchhoff.movies.data.ui.details.movie.UITrailersList
import com.kirchhoff.movies.repository.Result

interface IMovieDetailsMapper {
    fun createUIMovieDetails(movieDetailsResult: Result<NetworkMovieDetails>): Result<UIMovieDetails>
    fun createUIMovieCredits(movieCreditsResult: Result<NetworkMovieCredits>): Result<UIMovieCredits>
    fun createUITrailersList(trailersListResult: Result<NetworkTrailersList>): Result<UITrailersList>
}
