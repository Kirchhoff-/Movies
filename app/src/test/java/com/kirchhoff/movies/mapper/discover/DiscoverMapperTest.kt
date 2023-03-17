package com.kirchhoff.movies.mapper.discover

import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.data.ui.main.UIMovie
import com.kirchhoff.movies.data.ui.main.UITv
import com.kirchhoff.movies.networkdata.core.NetworkPaginated
import com.kirchhoff.movies.networkdata.main.NetworkMovie
import com.kirchhoff.movies.networkdata.main.NetworkTv
import com.kirchhoff.movies.utils.nextString
import kotlin.random.Random
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DiscoverMapperTest {

    private lateinit var discoverMapper: DiscoverMapper

    @Before
    fun setUp() {
        discoverMapper = DiscoverMapper()
    }

    @Test
    fun `verify creating discover movies list from error`() {
        val networkError = Result.Error<NetworkPaginated<NetworkMovie>>(errorCode)

        val expectedError = discoverMapper.createUIDiscoverMovieList(networkError)

        assertTrue(expectedError is Result.Error)
        assertEquals(errorCode, (expectedError as Result.Error).code)
        assertNull(expectedError.responseBody)
    }

    @Test
    fun `verify creating discover movies list from exception`() {
        val networkException = Result.Exception<NetworkPaginated<NetworkMovie>>(exceptionMessage)

        val expectedException = discoverMapper.createUIDiscoverMovieList(networkException)

        assertTrue(expectedException is Result.Exception)
        assertEquals(exceptionMessage, (expectedException as Result.Exception).message)
    }

    @Test
    fun `verify creating discover movies list without movies`() {
        val networkMovies = Result.Success<NetworkPaginated<NetworkMovie>>(
            NetworkPaginated(
                page,
                emptyList(),
                totalPages
            )
        )

        val uiMovies = discoverMapper.createUIDiscoverMovieList(networkMovies)

        assertTrue(uiMovies is Result.Success)
        assertEquals(page, (uiMovies as Result.Success).data.page)
        assertEquals(totalPages, uiMovies.data.totalPages)
        assertTrue(uiMovies.data.results.isEmpty())
    }

    @Test
    fun `verify creating discover movies list with movies`() {
        val networkMovie1 = createNetworkMovie(null, null)
        val networkMovie2 = createNetworkMovie(null, Random.nextString())
        val networkMovie3 = createNetworkMovie(Random.nextString(), null)
        val networkMovie4 = createNetworkMovie(Random.nextString(), Random.nextString())
        val networkMoviesList = listOf(networkMovie1, networkMovie2, networkMovie3, networkMovie4)

        val networkMovies = Result.Success(
            NetworkPaginated(
                page,
                networkMoviesList,
                totalPages
            )
        )

        val uiMovies = discoverMapper.createUIDiscoverMovieList(networkMovies)

        assertTrue(uiMovies is Result.Success)
        assertEquals(page, (uiMovies as Result.Success).data.page)
        assertEquals(totalPages, uiMovies.data.totalPages)
        assertEquals(networkMoviesList.size, uiMovies.data.results.size)
        assertMovies(networkMovie1, uiMovies.data.results[0])
        assertMovies(networkMovie2, uiMovies.data.results[1])
        assertMovies(networkMovie3, uiMovies.data.results[2])
        assertMovies(networkMovie4, uiMovies.data.results[3])
    }

    @Test
    fun `verify creating discover tv list from error`() {
        val networkError = Result.Error<NetworkPaginated<NetworkTv>>(errorCode)

        val expectedError = discoverMapper.createUIDiscoverTvList(networkError)

        assertTrue(expectedError is Result.Error)
        assertEquals(errorCode, (expectedError as Result.Error).code)
        assertNull(expectedError.responseBody)
    }

    @Test
    fun `verify creating discover tv list from exception`() {
        val networkException = Result.Exception<NetworkPaginated<NetworkTv>>(exceptionMessage)

        val expectedException = discoverMapper.createUIDiscoverTvList(networkException)

        assertTrue(expectedException is Result.Exception)
        assertEquals(exceptionMessage, (expectedException as Result.Exception).message)
    }

    @Test
    fun `verify creating discover tvs list without tvs`() {
        val networkTvs = Result.Success<NetworkPaginated<NetworkTv>>(
            NetworkPaginated(
                page,
                emptyList(),
                totalPages
            )
        )

        val uiTvs = discoverMapper.createUIDiscoverTvList(networkTvs)
        assertTrue(uiTvs is Result.Success)
        assertEquals(page, (uiTvs as Result.Success).data.page)
        assertEquals(totalPages, uiTvs.data.totalPages)
        assertTrue(uiTvs.data.results.isEmpty())
    }

    @Test
    fun `verify creating discover tvs list with tvs`() {
        val networkTv1 = createNetworkTv(null, null)
        val networkTv2 = createNetworkTv(null, Random.nextString())
        val networkTv3 = createNetworkTv(Random.nextString(), null)
        val networkTv4 = createNetworkTv(Random.nextString(), Random.nextString())
        val networkTvsList = listOf(networkTv1, networkTv2, networkTv3, networkTv4)

        val networkTvs = Result.Success(
            NetworkPaginated(
                page,
                networkTvsList,
                totalPages
            )
        )

        val uiTvs = discoverMapper.createUIDiscoverTvList(networkTvs)

        assertTrue(uiTvs is Result.Success)
        assertEquals(page, (uiTvs as Result.Success).data.page)
        assertEquals(totalPages, uiTvs.data.totalPages)
        assertEquals(networkTvsList.size, uiTvs.data.results.size)
        assertTvs(networkTv1, uiTvs.data.results[0])
        assertTvs(networkTv2, uiTvs.data.results[1])
        assertTvs(networkTv3, uiTvs.data.results[2])
        assertTvs(networkTv4, uiTvs.data.results[3])
    }

    private fun createNetworkMovie(posterPath: String?, backdropPath: String?) =
        NetworkMovie(
            Random.nextInt(),
            Random.nextString(),
            posterPath,
            backdropPath,
            Random.nextFloat()
        )

    private fun assertMovies(networkMovie: NetworkMovie, uiMovie: UIMovie) {
        assertEquals(uiMovie.id, networkMovie.id)
        assertEquals(uiMovie.backdropPath, networkMovie.backdropPath)
        assertEquals(uiMovie.posterPath, networkMovie.posterPath)
        assertEquals(uiMovie.title, networkMovie.title)
    }

    private fun createNetworkTv(posterPath: String?, backdropPath: String?) =
        NetworkTv(
            Random.nextInt(),
            posterPath,
            backdropPath,
            Random.nextString(),
            Random.nextFloat()
        )

    private fun assertTvs(networkTv: NetworkTv, uiTv: UITv) {
        assertEquals(uiTv.id, networkTv.id)
        assertEquals(uiTv.backdropPath, networkTv.backdropPath)
        assertEquals(uiTv.posterPath, networkTv.posterPath)
        assertEquals(uiTv.name, networkTv.name)
    }

    companion object {
        private val errorCode = Random.nextInt()
        private val exceptionMessage = Random.nextString()
        private val page = Random.nextInt(10)
        private val totalPages = Random.nextInt(1000)
    }
}
