package com.kirchhoff.movies.mapper.movie

import com.kirchhoff.movies.data.network.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.data.network.core.NetworkEntertainmentPerson
import com.kirchhoff.movies.data.network.core.NetworkObjectWithName
import com.kirchhoff.movies.data.network.details.movie.NetworkMovieDetails
import com.kirchhoff.movies.data.network.details.movie.NetworkTrailer
import com.kirchhoff.movies.data.network.details.movie.NetworkTrailersList
import com.kirchhoff.movies.data.ui.core.UIEntertainmentCredits
import com.kirchhoff.movies.data.ui.core.UIEntertainmentPerson
import com.kirchhoff.movies.data.ui.core.UIGenre
import com.kirchhoff.movies.data.ui.details.movie.UICountry
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

    override fun createUIEntertainmentCredits(movieCreditsResult: Result<NetworkEntertainmentCredits>): Result<UIEntertainmentCredits> =
        when (movieCreditsResult) {
            is Result.Success -> Result.Success(createUIEntertainmentCredits(movieCreditsResult.data))
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

    private fun createUIEntertainmentCredits(movieCredits: NetworkEntertainmentCredits) =
        UIEntertainmentCredits(
            movieCredits.cast?.map { createUIEntertainmentActor(it) },
            movieCredits.crew?.map { createUIEntertainmentCreator(it) }
        )

    private fun createUIEntertainmentActor(actor: NetworkEntertainmentPerson.Actor) =
        UIEntertainmentPerson.Actor(
            actor.id,
            actor.name,
            actor.profile_path,
            actor.character
        )

    private fun createUIEntertainmentCreator(creator: NetworkEntertainmentPerson.Creator) =
        UIEntertainmentPerson.Creator(
            creator.id,
            creator.name,
            creator.profile_path,
            creator.job
        )

    private fun createUITrailerList(trailer: NetworkTrailersList) =
        UITrailersList(trailer.results.map { createUITrailer(it) })

    private fun createUITrailer(trailer: NetworkTrailer) =
        UITrailer(trailer.site, trailer.key)

    private fun createUICountry(item: NetworkObjectWithName) = UICountry(item.name)

    private fun createUIGenre(item: NetworkObjectWithName) = UIGenre(item.name)
}
