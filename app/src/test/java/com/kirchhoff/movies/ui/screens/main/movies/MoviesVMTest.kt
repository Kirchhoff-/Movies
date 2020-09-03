package com.kirchhoff.movies.ui.screens.main.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kirchhoff.movies.data.ui.core.UIPaginated
import com.kirchhoff.movies.data.ui.main.UIMovie
import com.kirchhoff.movies.repository.Result
import com.kirchhoff.movies.repository.discover.IDiscoverRepository
import com.kirchhoff.movies.utils.CoroutineRule
import kotlin.random.Random
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
class MoviesVMTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val discoverRepository = mock(IDiscoverRepository::class.java)

    private lateinit var vm: MoviesVM

    @Before
    fun setUp() {
        vm = MoviesVM(discoverRepository)
    }

    @Test
    fun `verify error state after fetching first page`() = runBlockingTest {
        `when`(discoverRepository.fetchMovies(FIRST_PAGE)).thenReturn(ERROR)

        vm.fetchData(FIRST_PAGE)

        assert(vm.loading.value == false)
        assert(vm.paginating.value == null)
        assert(vm.error.value == ERROR.toString())
        assert(vm.data.value == null)
        verifyRepository(FIRST_PAGE)
    }

    @Test
    fun `verify exception state after fetching first page`() = runBlockingTest {
        `when`(discoverRepository.fetchMovies(FIRST_PAGE)).thenReturn(EXCEPTION)

        vm.fetchData(FIRST_PAGE)

        assert(vm.loading.value == false)
        assert(vm.paginating.value == null)
        assert(vm.error.value == EXCEPTION.toString())
        assert(vm.data.value == null)
        verifyRepository(FIRST_PAGE)
    }

    @Test
    fun `verify success state after fetching first page`() = runBlockingTest {
        `when`(discoverRepository.fetchMovies(FIRST_PAGE)).thenReturn(FIRST_PAGE_MOVIE_ANSWER)

        vm.fetchData(FIRST_PAGE)

        assert(vm.loading.value == false)
        assert(vm.paginating.value == null)
        assert(vm.error.value == null)
        assert(vm.data.value == FIRST_PAGE_MOVIE_ANSWER.data)
        verifyRepository(FIRST_PAGE)
    }

    @Test
    fun `verify error state after fetching random page`() = runBlockingTest {
        `when`(discoverRepository.fetchMovies(RANDOM_PAGE)).thenReturn(ERROR)

        vm.fetchData(RANDOM_PAGE)

        assert(vm.loading.value == null)
        assert(vm.paginating.value == false)
        assert(vm.error.value == ERROR.toString())
        assert(vm.data.value == null)
        verifyRepository(RANDOM_PAGE)
    }

    @Test
    fun `verify exception state after fetching random page`() = runBlockingTest {
        `when`(discoverRepository.fetchMovies(RANDOM_PAGE)).thenReturn(EXCEPTION)

        vm.fetchData(RANDOM_PAGE)

        assert(vm.loading.value == null)
        assert(vm.paginating.value == false)
        assert(vm.error.value == EXCEPTION.toString())
        assert(vm.data.value == null)
        verifyRepository(RANDOM_PAGE)
    }

    @Test
    fun `verify success state after fetching random page`() = runBlockingTest {
        `when`(discoverRepository.fetchMovies(RANDOM_PAGE)).thenReturn(RANDOM_PAGE_MOVIE_ANSWER)

        vm.fetchData(RANDOM_PAGE)

        assert(vm.loading.value == null)
        assert(vm.paginating.value == false)
        assert(vm.error.value == null)
        assert(vm.data.value == RANDOM_PAGE_MOVIE_ANSWER.data)
        verifyRepository(RANDOM_PAGE)
    }

    @Test
    fun `verify success first loading and error after paginated loading`() = runBlockingTest {
        `when`(discoverRepository.fetchMovies(FIRST_PAGE)).thenReturn(FIRST_PAGE_MOVIE_ANSWER)
        `when`(discoverRepository.fetchMovies(RANDOM_PAGE)).thenReturn(ERROR)

        vm.fetchData(FIRST_PAGE)

        assert(vm.paginating.value == null)
        assert(vm.loading.value == false)
        assert(vm.error.value == null)
        assert(vm.data.value == FIRST_PAGE_MOVIE_ANSWER.data)
        verify(discoverRepository).fetchMovies(FIRST_PAGE)

        vm.fetchData(RANDOM_PAGE)

        assert(vm.paginating.value == false)
        assert(vm.loading.value == false)
        assert(vm.error.value == ERROR.toString())
        assert(vm.data.value == FIRST_PAGE_MOVIE_ANSWER.data)
        verify(discoverRepository).fetchMovies(RANDOM_PAGE)

        verifyNoMoreInteractions(discoverRepository)
    }

    @Test
    fun `verify success first loading and exception after paginated loading`() = runBlockingTest {
        `when`(discoverRepository.fetchMovies(FIRST_PAGE)).thenReturn(FIRST_PAGE_MOVIE_ANSWER)
        `when`(discoverRepository.fetchMovies(RANDOM_PAGE)).thenReturn(EXCEPTION)

        vm.fetchData(FIRST_PAGE)

        assert(vm.paginating.value == null)
        assert(vm.loading.value == false)
        assert(vm.error.value == null)
        assert(vm.data.value == FIRST_PAGE_MOVIE_ANSWER.data)
        verify(discoverRepository).fetchMovies(FIRST_PAGE)

        vm.fetchData(RANDOM_PAGE)

        assert(vm.paginating.value == false)
        assert(vm.loading.value == false)
        assert(vm.error.value == EXCEPTION.toString())
        assert(vm.data.value == FIRST_PAGE_MOVIE_ANSWER.data)
        verify(discoverRepository).fetchMovies(RANDOM_PAGE)

        verifyNoMoreInteractions(discoverRepository)
    }

    @Test
    fun `verify success first loading and success paginated loading after`() = runBlockingTest {
        `when`(discoverRepository.fetchMovies(FIRST_PAGE)).thenReturn(FIRST_PAGE_MOVIE_ANSWER)
        `when`(discoverRepository.fetchMovies(RANDOM_PAGE)).thenReturn(RANDOM_PAGE_MOVIE_ANSWER)

        vm.fetchData(FIRST_PAGE)

        assert(vm.paginating.value == null)
        assert(vm.loading.value == false)
        assert(vm.error.value == null)
        assert(vm.data.value == FIRST_PAGE_MOVIE_ANSWER.data)
        verify(discoverRepository).fetchMovies(FIRST_PAGE)

        vm.fetchData(RANDOM_PAGE)

        assert(vm.paginating.value == false)
        assert(vm.loading.value == false)
        assert(vm.error.value == null)
        assert(vm.data.value == RANDOM_PAGE_MOVIE_ANSWER.data)
        verify(discoverRepository).fetchMovies(RANDOM_PAGE)

        verifyNoMoreInteractions(discoverRepository)
    }

    private fun verifyRepository(page: Int) = runBlockingTest {
        verify(discoverRepository).fetchMovies(page)
        verifyNoMoreInteractions(discoverRepository)
    }

    companion object {
        private val ERROR = Result.Error<UIPaginated<UIMovie>>(111)
        private val EXCEPTION = Result.Exception<UIPaginated<UIMovie>>(Exception("Movies mock exception"))
        private val RANDOM_PAGE = Random.nextInt(1, 100)
        private val FIRST_PAGE_MOVIE_ANSWER = Result.Success(UIPaginated(1, listOf(UIMovie(1, "First movie title", null, null)), Int.MAX_VALUE))
        private val RANDOM_PAGE_MOVIE_ANSWER = Result.Success(UIPaginated(1, listOf(UIMovie(2, "Random movie title", null, null)), Int.MAX_VALUE))
        private const val FIRST_PAGE = 1
    }
}
