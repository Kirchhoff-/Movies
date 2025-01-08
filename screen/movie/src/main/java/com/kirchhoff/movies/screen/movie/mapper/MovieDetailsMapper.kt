package com.kirchhoff.movies.screen.movie.mapper

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.ui.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.ui.UIImage
import com.kirchhoff.movies.core.data.ui.UIMovie
import com.kirchhoff.movies.core.mapper.BaseMapper
import com.kirchhoff.movies.core.mapper.ICoreMapper
import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.core.NetworkImagesResponse
import com.kirchhoff.movies.networkdata.core.NetworkProductionCompany
import com.kirchhoff.movies.networkdata.details.movie.NetworkCountry
import com.kirchhoff.movies.networkdata.details.movie.NetworkMovieDetails
import com.kirchhoff.movies.networkdata.details.movie.NetworkTrailer
import com.kirchhoff.movies.networkdata.details.movie.NetworkTrailersList
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import com.kirchhoff.movies.screen.movie.data.UICountry
import com.kirchhoff.movies.screen.movie.data.UIMovieInfo
import com.kirchhoff.movies.screen.movie.data.UIProductionCompany
import com.kirchhoff.movies.screen.movie.data.UITrailer

internal interface IMovieDetailsMapper {
    fun createUIMovie(networkMovie: NetworkMovie): UIMovie
    fun createUIMovieDetails(networkMovieDetails: NetworkMovieDetails): UIMovieInfo
    fun createUIEntertainmentCredits(networkMovieCredits: NetworkEntertainmentCredits): UIEntertainmentCredits
    fun createUITrailersList(networkTrailersList: NetworkTrailersList): List<UITrailer>
    fun createUIImages(networkImagesResponse: NetworkImagesResponse): List<UIImage>
}

internal class MovieDetailsMapper(private val coreMapper: ICoreMapper) : BaseMapper(), IMovieDetailsMapper {

    override fun createUIMovie(networkMovie: NetworkMovie): UIMovie = networkMovie.toUIMovie()

    override fun createUIMovieDetails(networkMovieDetails: NetworkMovieDetails): UIMovieInfo = networkMovieDetails.toUIMovie()

    override fun createUIEntertainmentCredits(networkMovieCredits: NetworkEntertainmentCredits): UIEntertainmentCredits =
        coreMapper.createUIEntertainmentCredits(networkMovieCredits)

    override fun createUITrailersList(networkTrailersList: NetworkTrailersList): List<UITrailer> =
        networkTrailersList.toUITrailerList()

    override fun createUIImages(networkImagesResponse: NetworkImagesResponse): List<UIImage> =
        networkImagesResponse.combinedImages().map { coreMapper.createUIImage(it) }

    private fun NetworkMovie.toUIMovie(): UIMovie = UIMovie(
        id = MovieId(id),
        title = title,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage
    )

    private fun NetworkMovieDetails.toUIMovie(): UIMovieInfo = UIMovieInfo(
        productionCountries = productionCountries.map { it.toUICountry() },
        productionCompanies = productionCompanies.map { it.toUIProductionCompany() },
        runtime = runtime,
        tagLine = tagline,
        overview = overview,
        releaseDate = releaseDate,
        voteCount = voteCount,
        voteAverage = voteAverage,
        genres = genres.map { coreMapper.createUIGenre(it) }
    )

    private fun NetworkTrailersList.toUITrailerList(): List<UITrailer> = results.map { it.toUITrailer() }

    private fun NetworkTrailer.toUITrailer(): UITrailer = UITrailer(
        site = site,
        key = key
    )

    private fun NetworkCountry.toUICountry(): UICountry = UICountry(
        id = id,
        name = name
    )

    private fun NetworkProductionCompany.toUIProductionCompany(): UIProductionCompany = UIProductionCompany(
        id = id,
        logoPath = logoPath.orEmpty(),
        name = name
    )
}
