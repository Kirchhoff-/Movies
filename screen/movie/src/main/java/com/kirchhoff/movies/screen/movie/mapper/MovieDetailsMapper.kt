package com.kirchhoff.movies.screen.movie.mapper

import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UIImage
import com.kirchhoff.movies.core.mapper.BaseMapper
import com.kirchhoff.movies.core.mapper.ICoreMapper
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.core.NetworkImagesResponse
import com.kirchhoff.movies.networkdata.core.NetworkProductionCompany
import com.kirchhoff.movies.networkdata.details.movie.NetworkCountry
import com.kirchhoff.movies.networkdata.details.movie.NetworkMovieDetails
import com.kirchhoff.movies.networkdata.details.movie.NetworkTrailer
import com.kirchhoff.movies.networkdata.details.movie.NetworkTrailersList
import com.kirchhoff.movies.screen.movie.data.UICountry
import com.kirchhoff.movies.screen.movie.data.UIMovieInfo
import com.kirchhoff.movies.screen.movie.data.UIProductionCompany
import com.kirchhoff.movies.screen.movie.data.UITrailer

internal interface IMovieDetailsMapper {
    fun createUIMovieDetails(movieDetailsResult: Result<NetworkMovieDetails>): Result<UIMovieInfo>
    fun createUIEntertainmentCredits(movieCreditsResult: Result<NetworkEntertainmentCredits>): Result<UIEntertainmentCredits>
    fun createUITrailersList(trailersListResult: Result<NetworkTrailersList>): Result<List<UITrailer>>
    fun createUIImages(imagesResponseResult: Result<NetworkImagesResponse>): Result<List<UIImage>>
}

internal class MovieDetailsMapper(private val coreMapper: ICoreMapper) : BaseMapper(), IMovieDetailsMapper {
    override fun createUIMovieDetails(movieDetailsResult: Result<NetworkMovieDetails>): Result<UIMovieInfo> =
        when (movieDetailsResult) {
            is Result.Success -> Result.Success(createUIMovieDetails(movieDetailsResult.data))
            else -> mapErrorOrException(movieDetailsResult)
        }

    override fun createUIEntertainmentCredits(movieCreditsResult: Result<NetworkEntertainmentCredits>): Result<UIEntertainmentCredits> =
        when (movieCreditsResult) {
            is Result.Success -> Result.Success(coreMapper.createUIEntertainmentCredits(movieCreditsResult.data))
            else -> mapErrorOrException(movieCreditsResult)
        }

    override fun createUITrailersList(trailersListResult: Result<NetworkTrailersList>): Result<List<UITrailer>> =
        when (trailersListResult) {
            is Result.Success -> Result.Success(createUITrailerList(trailersListResult.data))
            else -> mapErrorOrException(trailersListResult)
        }

    override fun createUIImages(imagesResponseResult: Result<NetworkImagesResponse>): Result<List<UIImage>> =
        when (imagesResponseResult) {
            is Result.Success -> Result.Success(imagesResponseResult.data.combinedImages().map { coreMapper.createUIImage(it) })
            else -> mapErrorOrException(imagesResponseResult)
        }

    private fun createUIMovieDetails(movieDetails: NetworkMovieDetails) =
        UIMovieInfo(
            movieDetails.productionCountries.map { createUICountry(it) },
            movieDetails.productionCompanies.map { createUIProductionCompany(it) },
            movieDetails.runtime,
            movieDetails.tagline,
            movieDetails.overview,
            movieDetails.releaseDate,
            movieDetails.voteCount,
            movieDetails.voteAverage,
            movieDetails.genres.map { coreMapper.createUIGenre(it) }
        )

    private fun createUITrailerList(trailer: NetworkTrailersList) =
        trailer.results.map { createUITrailer(it) }

    private fun createUITrailer(trailer: NetworkTrailer) =
        UITrailer(trailer.site, trailer.key)

    private fun createUICountry(item: NetworkCountry) = UICountry(item.id, item.name)

    private fun createUIProductionCompany(item: NetworkProductionCompany) = UIProductionCompany(
        id = item.id,
        logoPath = item.logoPath.orEmpty(),
        name = item.name
    )
}
