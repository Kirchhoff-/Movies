package com.kirchhoff.movies.screen.movie.mapper

import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.data.ui.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.ui.UIImage
import com.kirchhoff.movies.core.data.ui.UIMovie
import com.kirchhoff.movies.core.mapper.BaseMapper
import com.kirchhoff.movies.core.mapper.CoreMapper
import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.core.NetworkImagesResponse
import com.kirchhoff.movies.networkdata.core.NetworkProductionCompany
import com.kirchhoff.movies.networkdata.details.movie.NetworkCountry
import com.kirchhoff.movies.networkdata.details.movie.NetworkMovieDetails
import com.kirchhoff.movies.networkdata.details.movie.NetworkTrailer
import com.kirchhoff.movies.networkdata.details.movie.NetworkTrailersList
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import com.kirchhoff.movies.screen.movie.data.MovieUICountry
import com.kirchhoff.movies.screen.movie.data.MovieUIInfo
import com.kirchhoff.movies.screen.movie.data.MovieUIProductionCompany
import com.kirchhoff.movies.screen.movie.data.MovieUITrailer

internal class MovieDetailsMapper(private val coreMapper: CoreMapper) : BaseMapper() {

    fun createUIMovie(networkMovie: NetworkMovie): UIMovie = networkMovie.toUIMovie()

    fun createUIMovieDetails(networkMovieDetails: NetworkMovieDetails): MovieUIInfo = networkMovieDetails.toUIMovie()

    fun createUIEntertainmentCredits(networkMovieCredits: NetworkEntertainmentCredits): UIEntertainmentCredits =
        coreMapper.createUIEntertainmentCredits(networkMovieCredits)

    fun createMovieTrailersList(networkTrailersList: NetworkTrailersList): List<MovieUITrailer> =
        networkTrailersList.toMovieTrailerList()

    fun createUIImages(networkImagesResponse: NetworkImagesResponse): List<UIImage> =
        networkImagesResponse.combinedImages().map { coreMapper.createUIImage(it) }

    private fun NetworkMovie.toUIMovie(): UIMovie = UIMovie(
        id = MovieId(id),
        title = title,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage
    )

    private fun NetworkMovieDetails.toUIMovie(): MovieUIInfo = MovieUIInfo(
        productionCountries = productionCountries.map { it.toMovieCountry() },
        productionCompanies = productionCompanies.map { it.toMovieProductionCompany() },
        runtime = runtime,
        tagLine = tagline,
        overview = overview,
        releaseDate = releaseDate,
        voteCount = voteCount,
        voteAverage = voteAverage,
        genres = genres.map { coreMapper.createUIGenre(it) }
    )

    private fun NetworkTrailersList.toMovieTrailerList(): List<MovieUITrailer> = results.map { it.toMovieTrailer() }

    private fun NetworkTrailer.toMovieTrailer(): MovieUITrailer = MovieUITrailer(
        site = site,
        key = key
    )

    private fun NetworkCountry.toMovieCountry(): MovieUICountry = MovieUICountry(
        id = id,
        name = name
    )

    private fun NetworkProductionCompany.toMovieProductionCompany(): MovieUIProductionCompany = MovieUIProductionCompany(
        id = id,
        logoPath = logoPath.orEmpty(),
        name = name
    )
}
