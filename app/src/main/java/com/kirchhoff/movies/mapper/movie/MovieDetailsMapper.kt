package com.kirchhoff.movies.mapper.movie

import com.kirchhoff.movies.data.network.core.NetworkObjectWithName
import com.kirchhoff.movies.data.network.details.movie.NetworkMovieCastCredit
import com.kirchhoff.movies.data.network.details.movie.NetworkMovieCredits
import com.kirchhoff.movies.data.network.details.movie.NetworkMovieCrewCredit
import com.kirchhoff.movies.data.network.details.movie.NetworkMovieDetails
import com.kirchhoff.movies.data.network.details.movie.NetworkTrailer
import com.kirchhoff.movies.data.network.details.movie.NetworkTrailersList
import com.kirchhoff.movies.data.ui.core.UIGenre
import com.kirchhoff.movies.data.ui.details.movie.UICountry
import com.kirchhoff.movies.data.ui.details.movie.UIMovieCastCredit
import com.kirchhoff.movies.data.ui.details.movie.UIMovieCredits
import com.kirchhoff.movies.data.ui.details.movie.UIMovieCrewCredit
import com.kirchhoff.movies.data.ui.details.movie.UIMovieDetails
import com.kirchhoff.movies.data.ui.details.movie.UITrailer
import com.kirchhoff.movies.data.ui.details.movie.UITrailersList
import com.kirchhoff.movies.mapper.BaseMapper
import com.kirchhoff.movies.repository.Result

class MovieDetailsMapper : BaseMapper(), IMovieDetailsMapper {
    override fun createUIMovieDetails(movieDetailsResult: Result<NetworkMovieDetails>): Result<UIMovieDetails> =
        when (movieDetailsResult) {
            is Result.Success -> Result.Success(createUIMovieDetails(movieDetailsResult.data))
            else -> mapErrorOrException(movieDetailsResult)
        }

    override fun createUIMovieCredits(movieCreditsResult: Result<NetworkMovieCredits>): Result<UIMovieCredits> =
        when (movieCreditsResult) {
            is Result.Success -> Result.Success(createUIMovieCredits(movieCreditsResult.data))
            else -> mapErrorOrException(movieCreditsResult)
        }

    override fun createUITrailersList(trailersListResult: Result<NetworkTrailersList>): Result<UITrailersList> =
        when (trailersListResult) {
            is Result.Success -> Result.Success(createUITrailerList(trailersListResult.data))
            else -> mapErrorOrException(trailersListResult)
        }

    private fun createUIMovieDetails(movieDetails: NetworkMovieDetails) =
        UIMovieDetails(
            movieDetails.production_countries.map { createUICountry(it) },
            movieDetails.runtime,
            movieDetails.tagline,
            movieDetails.overview,
            movieDetails.release_date,
            movieDetails.vote_count,
            movieDetails.vote_average,
            movieDetails.genres.map { createUIGenre(it) }
        )

    private fun createUIMovieCredits(movieCredits: NetworkMovieCredits) =
        UIMovieCredits(
            movieCredits.cast?.map { createUIMovieCastCredit(it) },
            movieCredits.crew?.map { createUIMovieCrewCredit(it) }
        )

    private fun createUIMovieCastCredit(castCredit: NetworkMovieCastCredit) =
        UIMovieCastCredit(
            castCredit.id,
            castCredit.name,
            castCredit.profile_path,
            castCredit.character
        )

    private fun createUIMovieCrewCredit(crewCredit: NetworkMovieCrewCredit) =
        UIMovieCrewCredit(
            crewCredit.id,
            crewCredit.name,
            crewCredit.profile_path,
            crewCredit.job
        )

    private fun createUITrailerList(trailer: NetworkTrailersList) =
        UITrailersList(trailer.results.map { createUITrailer(it) })

    private fun createUITrailer(trailer: NetworkTrailer) =
        UITrailer(trailer.site, trailer.key)

    private fun createUICountry(item: NetworkObjectWithName) =
        UICountry(item.name)

    private fun createUIGenre(item: NetworkObjectWithName) =
        UIGenre(item.name)
}
