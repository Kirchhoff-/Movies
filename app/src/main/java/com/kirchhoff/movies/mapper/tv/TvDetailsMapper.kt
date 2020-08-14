package com.kirchhoff.movies.mapper.tv

import com.kirchhoff.movies.data.network.core.NetworkObjectWithName
import com.kirchhoff.movies.data.network.details.tv.NetworkTvCastCredit
import com.kirchhoff.movies.data.network.details.tv.NetworkTvCredits
import com.kirchhoff.movies.data.network.details.tv.NetworkTvCrewCredit
import com.kirchhoff.movies.data.network.details.tv.NetworkTvDetails
import com.kirchhoff.movies.data.ui.core.UIGenre
import com.kirchhoff.movies.data.ui.details.tv.UITvCastCredit
import com.kirchhoff.movies.data.ui.details.tv.UITvCredits
import com.kirchhoff.movies.data.ui.details.tv.UITvCrewCredit
import com.kirchhoff.movies.data.ui.details.tv.UITvDetails
import com.kirchhoff.movies.mapper.BaseMapper
import com.kirchhoff.movies.repository.Result

class TvDetailsMapper : BaseMapper(), ITvDetailsMapper {

    override fun createUITvDetails(tvDetailsResult: Result<NetworkTvDetails>): Result<UITvDetails> =
        when (tvDetailsResult) {
            is Result.Success -> Result.Success(createUITvDetails(tvDetailsResult.data))
            else -> mapErrorOrException(tvDetailsResult)
        }

    override fun createUITvCredits(tvCreditsResult: Result<NetworkTvCredits>): Result<UITvCredits> =
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

    private fun createUITvCredits(tvCredits: NetworkTvCredits) =
        UITvCredits(
            tvCredits.cast?.map { createUITvCastCredit(it) },
            tvCredits.crew?.map { createUITvCrewCredit(it) }
        )

    private fun createUITvCastCredit(castCredit: NetworkTvCastCredit) =
        UITvCastCredit(
            castCredit.name,
            castCredit.character,
            castCredit.profile_path
        )

    private fun createUITvCrewCredit(crewCredit: NetworkTvCrewCredit) =
        UITvCrewCredit(
            crewCredit.name,
            crewCredit.job,
            crewCredit.poster_path
        )

    private fun createUIGenre(item: NetworkObjectWithName) =
        UIGenre(item.name)
}
