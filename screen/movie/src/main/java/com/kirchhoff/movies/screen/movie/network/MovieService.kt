package com.kirchhoff.movies.screen.movie.network

import com.kirchhoff.movies.networkdata.core.NetworkEntertainmentCredits
import com.kirchhoff.movies.networkdata.core.NetworkImagesResponse
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.details.movie.NetworkMovieDetails
import com.kirchhoff.movies.networkdata.details.movie.NetworkTrailersList
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("/3/discover/movie?language=en&sort_by=popularity.desc")
    suspend fun fetchDiscoverList(@Query("page") page: Int): Response<NetworkPaginated<NetworkMovie>>

    @GET("/3/movie/{movie_id}")
    suspend fun fetchDetails(@Path("movie_id") id: Int): Response<NetworkMovieDetails>

    @GET("/3/movie/{movie_id}/similar")
    suspend fun fetchSimilarMovies(
        @Path("movie_id") id: Int,
        @Query("page") page: Int
    ): Response<NetworkPaginated<NetworkMovie>>

    @GET("/3/discover/movie?language=en&sort_by=popularity.desc")
    suspend fun fetchByCountry(
        @Query("with_origin_country") countryId: String,
        @Query("page") page: Int
    ): Response<NetworkPaginated<NetworkMovie>>

    @GET("/3/movie/{movie_id}/videos")
    suspend fun fetchTrailersList(@Path("movie_id") id: Int): Response<NetworkTrailersList>

    @GET("/3/movie/{movie_id}/credits")
    suspend fun fetchMovieCredits(@Path("movie_id") id: Int): Response<NetworkEntertainmentCredits>

    @GET("/3/discover/movie?language=en&sort_by=popularity.desc")
    suspend fun fetchByGenre(
        @Query("with_genres") genre: String,
        @Query("page") page: Int
    ): Response<NetworkPaginated<NetworkMovie>>

    @GET("/3/movie/{movie_id}/images?language=en")
    suspend fun fetchImages(@Path("movie_id") id: Int): Response<NetworkImagesResponse>
}
