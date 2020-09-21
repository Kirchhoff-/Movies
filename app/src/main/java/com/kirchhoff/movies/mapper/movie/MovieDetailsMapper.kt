package com.kirchhoff.movies.mapper.movie

import com.kirchhoff.movies.data.network.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.data.network.core.NetworkObjectWithName
import com.kirchhoff.movies.data.network.details.movie.NetworkMovieDetails
import com.kirchhoff.movies.data.network.details.movie.NetworkTrailer
import com.kirchhoff.movies.data.network.details.movie.NetworkTrailersList
import com.kirchhoff.movies.data.ui.core.UIEntertainmentCredits
import com.kirchhoff.movies.data.ui.details.movie.UICountry
import com.kirchhoff.movies.data.ui.details.movie.UIMovieDetails
import com.kirchhoff.movies.data.ui.details.movie.UITrailer
import com.kirchhoff.movies.data.ui.details.movie.UITrailersList
import com.kirchhoff.movies.mapper.BaseMapper
import com.kirchhoff.movies.mapper.core.ICoreMapper
import com.kirchhoff.movies.repository.Result

class MovieDetailsMapper(private val coreMapper: ICoreMapper) : BaseMapper(), IMovieDetailsMapper {
    override fun createUIMovieDetails(movieDetailsResult: Result<NetworkMovieDetails>): Result<UIMovieDetails> =
        when (movieDetailsResult) {
            is Result.Success -> Result.Success(createUIMovieDetails(movieDetailsResult.data))
            else -> mapErrorOrException(movieDetailsResult)
        }

    override fun createUIEntertainmentCredits(movieCreditsResult: Result<NetworkEntertainmentCredits>): Result<UIEntertainmentCredits> =
        when (movieCreditsResult) {
            is Result.Success -> Result.Success(coreMapper.createUIEntertainmentCredits(movieCreditsResult.data))
            else -> mapErrorOrException(movieCreditsResult)
        }

    override fun createUITrailersList(trailersListResult: Result<NetworkTrailersList>): Result<UITrailersList> =
        when (trailersListResult) {
            is Result.Success -> Result.Success(createUITrailerList(trailersListResult.data))
            else -> mapErrorOrException(trailersListResult)
        }

    private fun createUIMovieDetails(movieDetails: NetworkMovieDetails) =
        UIMovieDetails(
            movieDetails.productionCountries.map { createUICountry(it) },
            movieDetails.runtime,
            movieDetails.tagline,
            movieDetails.overview,
            movieDetails.releaseDate,
            movieDetails.voteCount,
            movieDetails.voteAverage,
            movieDetails.genres.map { coreMapper.createUIGenre(it) }
        )

    private fun createUITrailerList(trailer: NetworkTrailersList) =
        UITrailersList(trailer.results.map { createUITrailer(it) })

    private fun createUITrailer(trailer: NetworkTrailer) =
        UITrailer(trailer.site, trailer.key)

    private fun createUICountry(item: NetworkObjectWithName) = UICountry(item.name)
}
