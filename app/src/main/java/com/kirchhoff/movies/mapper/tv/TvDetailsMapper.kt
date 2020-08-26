package com.kirchhoff.movies.mapper.tv

import com.kirchhoff.movies.data.network.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.data.network.core.NetworkEntertainmentPerson
import com.kirchhoff.movies.data.network.core.NetworkObjectWithName
import com.kirchhoff.movies.data.network.details.tv.NetworkTvDetails
import com.kirchhoff.movies.data.ui.core.UIEntertainmentCredits
import com.kirchhoff.movies.data.ui.core.UIEntertainmentPerson
import com.kirchhoff.movies.data.ui.core.UIGenre
import com.kirchhoff.movies.data.ui.details.tv.UITvDetails
import com.kirchhoff.movies.mapper.BaseMapper
import com.kirchhoff.movies.repository.Result

class TvDetailsMapper : BaseMapper(), ITvDetailsMapper {

    override fun createUITvDetails(tvDetailsResult: Result<NetworkTvDetails>): Result<UITvDetails> =
        when (tvDetailsResult) {
            is Result.Success -> Result.Success(createUITvDetails(tvDetailsResult.data))
            else -> mapErrorOrException(tvDetailsResult)
        }

    override fun createUIEntertainmentCredits(tvCreditsResult: Result<NetworkEntertainmentCredits>): Result<UIEntertainmentCredits> =
        when (tvCreditsResult) {
            is Result.Success -> Result.Success(createUITvCredits(tvCreditsResult.data))
            else -> mapErrorOrException(tvCreditsResult)
        }

    private fun createUITvDetails(tvDetails: NetworkTvDetails) =
        UITvDetails(
            tvDetails.number_of_seasons,
            tvDetails.number_of_episodes,
            tvDetails.overview,
            tvDetails.status,
            tvDetails.first_air_date,
            tvDetails.vote_count,
            tvDetails.vote_average,
            tvDetails.genres.map { createUIGenre(it) }
        )

    private fun createUITvCredits(tvCredits: NetworkEntertainmentCredits) =
        UIEntertainmentCredits(
            tvCredits.cast?.map { createUIEntertainmentActor(it) },
            tvCredits.crew?.map { createUIEntertainmentCreator(it) }
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

    private fun createUIGenre(item: NetworkObjectWithName) =
        UIGenre(item.name)
}
