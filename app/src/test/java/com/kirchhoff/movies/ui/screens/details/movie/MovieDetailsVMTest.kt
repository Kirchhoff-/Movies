package com.kirchhoff.movies.ui.screens.details.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kirchhoff.movies.data.ui.core.UIEntertainmentCredits
import com.kirchhoff.movies.data.ui.details.movie.UIMovieDetails
import com.kirchhoff.movies.data.ui.details.movie.UITrailersList
import com.kirchhoff.movies.repository.Result
import com.kirchhoff.movies.repository.movie.IMovieRepository
import com.kirchhoff.movies.utils.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.junit.MockitoJUnitRunner

@Suppress("EXPERIMENTAL_API_USAGE")
@RunWith(MockitoJUnitRunner::class)
class MovieDetailsVMTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val movieRepository = mock(IMovieRepository::class.java)

    private lateinit var vm: MovieDetailsVM

    @Before
    fun setUp() {
        vm = MovieDetailsVM(movieRepository)
    }

    @Test
    fun `verify error state after fetching movie details`() = runBlockingTest {
        val error = Result.Error<UIMovieDetails>(ERROR_CODE)
        `when`(movieRepository.fetchDetails(MOVIE_ID)).thenReturn(error)

        vm.loadMovieDetails(MOVIE_ID)

        assert(vm.movieDetails.value == null)
        assert(vm.error.value == error.toString())
        assert(vm.exception.value == null)
        assert(vm.trailers.value == null)
        assert(vm.movieCredits.value == null)
        assert(vm.loading.value == false)
        verify(movieRepository).fetchDetails(MOVIE_ID)
        verifyNoMoreInteractions(movieRepository)
    }

    @Test
    fun `verify exception state after fetching movie details`() = runBlockingTest {
        val exception = Result.Exception<UIMovieDetails>(EXCEPTION)
        `when`(movieRepository.fetchDetails(MOVIE_ID)).thenReturn(exception)

        vm.loadMovieDetails(MOVIE_ID)

        assert(vm.movieDetails.value == null)
        assert(vm.error.value == null)
        assert(vm.exception.value == exception.toString())
        assert(vm.trailers.value == null)
        assert(vm.movieCredits.value == null)
        assert(vm.loading.value == false)
        verify(movieRepository).fetchDetails(MOVIE_ID)
        verifyNoMoreInteractions(movieRepository)
    }

    @Test
    fun `verify success state after fetching movie details`() = runBlockingTest {
        mockSuccessMovieDetails()

        vm.loadMovieDetails(MOVIE_ID)

        verifyRequests()
    }

    @Test
    fun `verify error state after fetching movie trailers`() = runBlockingTest {
        mockSuccessMovieDetails()
        val error = Result.Error<UITrailersList>(ERROR_CODE)
        `when`(movieRepository.fetchTrailersList(MOVIE_ID)).thenReturn(error)

        vm.loadMovieDetails(MOVIE_ID)

        assert(vm.trailers.value == null)
        verifyRequests()
    }

    @Test
    fun `verify exception state after fetching movie trailers`() = runBlockingTest {
        mockSuccessMovieDetails()
        val exception = Result.Exception<UITrailersList>(EXCEPTION)
        `when`(movieRepository.fetchTrailersList(MOVIE_ID)).thenReturn(exception)

        vm.loadMovieDetails(MOVIE_ID)

        assert(vm.trailers.value == null)
        verifyRequests()
    }

    @Test
    fun `verify success state after fetching movie trailers`() = runBlockingTest {
        mockSuccessMovieDetails()
        val movieTrailers = Result.Success(MOVIE_TRAILERS)
        `when`(movieRepository.fetchTrailersList(MOVIE_ID)).thenReturn(movieTrailers)

        vm.loadMovieDetails(MOVIE_ID)

        assert(vm.trailers.value == MOVIE_TRAILERS.results)
        verifyRequests()
    }

    @Test
    fun `verify error state after fetching movie credits`() = runBlockingTest {
        mockSuccessMovieDetails()
        val error = Result.Error<UIEntertainmentCredits>(ERROR_CODE)
        `when`(movieRepository.fetchMovieCredits(MOVIE_ID)).thenReturn(error)

        vm.loadMovieDetails(MOVIE_ID)

        assert(vm.movieCredits.value == null)
        verifyRequests()
    }

    @Test
    fun `verify exception state after fetching movie credits`() = runBlockingTest {
        mockSuccessMovieDetails()
        val exception = Result.Exception<UIEntertainmentCredits>(EXCEPTION)
        `when`(movieRepository.fetchMovieCredits(MOVIE_ID)).thenReturn(exception)

        vm.loadMovieDetails(MOVIE_ID)

        assert(vm.movieCredits.value == null)
        verifyRequests()
    }

    @Test
    fun `verify success state after fetching movie credits`() = runBlockingTest {
        mockSuccessMovieDetails()
        val movieCredits = Result.Success(MOVIE_CREDITS)
        `when`(movieRepository.fetchMovieCredits(MOVIE_ID)).thenReturn(movieCredits)

        vm.loadMovieDetails(MOVIE_ID)

        assert(vm.movieCredits.value == MOVIE_CREDITS)
        verifyRequests()
    }

    @Test
    fun `verify success state of trailers after error during fetching of credits`() = runBlockingTest {
        mockSuccessMovieDetails()
        val movieTrailers = Result.Success(MOVIE_TRAILERS)
        val error = Result.Error<UIEntertainmentCredits>(ERROR_CODE)
        `when`(movieRepository.fetchTrailersList(MOVIE_ID)).thenReturn(movieTrailers)
        `when`(movieRepository.fetchMovieCredits(MOVIE_ID)).thenReturn(error)

        vm.loadMovieDetails(MOVIE_ID)

        assert(vm.trailers.value == MOVIE_TRAILERS.results)
        assert(vm.movieCredits.value == null)
        verifyRequests()
    }

    @Test
    fun `verify success state of trailers after exception during fetching of credits`() = runBlockingTest {
        mockSuccessMovieDetails()
        val movieTrailers = Result.Success(MOVIE_TRAILERS)
        val exception = Result.Exception<UIEntertainmentCredits>(EXCEPTION)
        `when`(movieRepository.fetchTrailersList(MOVIE_ID)).thenReturn(movieTrailers)
        `when`(movieRepository.fetchMovieCredits(MOVIE_ID)).thenReturn(exception)

        vm.loadMovieDetails(MOVIE_ID)

        assert(vm.trailers.value == MOVIE_TRAILERS.results)
        assert(vm.movieCredits.value == null)
        verifyRequests()
    }

    @Test
    fun `verify success state of credits after error during fetching of trailers`() = runBlockingTest {
        mockSuccessMovieDetails()
        val movieCredits = Result.Success(MOVIE_CREDITS)
        val error = Result.Error<UITrailersList>(ERROR_CODE)
        `when`(movieRepository.fetchMovieCredits(MOVIE_ID)).thenReturn(movieCredits)
        `when`(movieRepository.fetchTrailersList(MOVIE_ID)).thenReturn(error)

        vm.loadMovieDetails(MOVIE_ID)

        assert(vm.movieCredits.value == MOVIE_CREDITS)
        assert(vm.trailers.value == null)
        verifyRequests()
    }

    @Test
    fun `verify success state of credits after exception during fetching of trailers`() = runBlockingTest {
        mockSuccessMovieDetails()
        val movieCredits = Result.Success(MOVIE_CREDITS)
        val exception = Result.Exception<UITrailersList>(EXCEPTION)
        `when`(movieRepository.fetchMovieCredits(MOVIE_ID)).thenReturn(movieCredits)
        `when`(movieRepository.fetchTrailersList(MOVIE_ID)).thenReturn(exception)

        vm.loadMovieDetails(MOVIE_ID)

        assert(vm.movieCredits.value == MOVIE_CREDITS)
        assert(vm.trailers.value == null)
        verifyRequests()
    }

    private fun mockSuccessMovieDetails() = runBlockingTest {
        val movieDetails = Result.Success(MOVIE_DETAILS)
        `when`(movieRepository.fetchDetails(MOVIE_ID)).thenReturn(movieDetails)
    }

    private fun verifyRequests() = runBlockingTest {
        verify(movieRepository).fetchTrailersList(MOVIE_ID)
        verify(movieRepository).fetchMovieCredits(MOVIE_ID)
        verifyFetchingMovieDetails()
        verifyNoMoreInteractions(movieRepository)
    }

    private fun verifyFetchingMovieDetails() = runBlockingTest {
        assert(vm.movieDetails.value == MOVIE_DETAILS)
        assert(vm.error.value == null)
        assert(vm.exception.value == null)
        assert(vm.loading.value == false)

        verify(movieRepository).fetchDetails(MOVIE_ID)
    }

    companion object {
        private const val MOVIE_ID = 789
        private const val ERROR_CODE = 777
        private val MOVIE_DETAILS = UIMovieDetails(emptyList(), 180, "Tagline", "overview", null, 5, 5f, emptyList())
        private val MOVIE_TRAILERS = UITrailersList(emptyList())
        private val MOVIE_CREDITS = UIEntertainmentCredits(emptyList(), emptyList())
        private val EXCEPTION = Exception("Mock movie exception")
    }
}
