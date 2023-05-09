package com.kirchhoff.movies.ui.screens.details.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.data.ui.details.tv.UITvDetails
import com.kirchhoff.movies.repository.tv.ITvRepository
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
class TvDetailsVMTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val tvRepository = mock(ITvRepository::class.java)

    private lateinit var vm: TvDetailsVM

    @Before
    fun setUp() {
        vm = TvDetailsVM(tvRepository)
    }

    @Test
    fun `verify error state after fetching tv details`() = runBlockingTest {
        val error = Result.Error<UITvDetails>(ERROR_CODE)
        `when`(tvRepository.fetchDetails(TV_ID)).thenReturn(error)

        vm.loadTvDetails(TV_ID)

        assert(vm.tvDetails.value == null)
        assert(vm.error.value == error.toString())
        assert(vm.exception.value == null)
        assert(vm.tvCredits.value == null)
        assert(vm.loading.value == false)
        verify(tvRepository).fetchDetails(TV_ID)
        verifyNoMoreInteractions(tvRepository)
    }

    @Test
    fun `verify exception state after fetching tv details`() = runBlockingTest {
        val exception = Result.Exception<UITvDetails>(EXCEPTION)
        `when`(tvRepository.fetchDetails(TV_ID)).thenReturn(exception)

        vm.loadTvDetails(TV_ID)

        assert(vm.tvDetails.value == null)
        assert(vm.error.value == null)
        assert(vm.exception.value == exception.toString())
        assert(vm.tvCredits.value == null)
        assert(vm.loading.value == false)
        verify(tvRepository).fetchDetails(TV_ID)
        verifyNoMoreInteractions(tvRepository)
    }

    @Test
    fun `verify success state after fetching tv details`() = runBlockingTest {
        mockSuccessTvDetails()

        vm.loadTvDetails(TV_ID)

        verifyRequests()
    }

    @Test
    fun `verify error state after fetching tv credits`() = runBlockingTest {
        mockSuccessTvDetails()
        val error = Result.Error<UIEntertainmentCredits>(ERROR_CODE)
        `when`(tvRepository.fetchTvCredits(TV_ID)).thenReturn(error)

        vm.loadTvDetails(TV_ID)

        assert(vm.tvCredits.value == null)
        verifyRequests()
    }

    @Test
    fun `verify exception state after fetching tv credits`() = runBlockingTest {
        mockSuccessTvDetails()
        val exception = Result.Exception<UIEntertainmentCredits>(EXCEPTION)
        `when`(tvRepository.fetchTvCredits(TV_ID)).thenReturn(exception)

        vm.loadTvDetails(TV_ID)

        assert(vm.tvCredits.value == null)
        verifyRequests()
    }

    @Test
    fun `verify success state after fetching tv credits`() = runBlockingTest {
        mockSuccessTvDetails()
        val tvCredits = Result.Success(TV_CREDITS)
        `when`(tvRepository.fetchTvCredits(TV_ID)).thenReturn(tvCredits)

        vm.loadTvDetails(TV_ID)

        assert(vm.tvCredits.value == TV_CREDITS)
        verifyRequests()
    }

    private fun mockSuccessTvDetails() = runBlockingTest {
        val tvDetails = Result.Success(TV_DETAILS)
        `when`(tvRepository.fetchDetails(TV_ID)).thenReturn(tvDetails)
    }

    private fun verifyRequests() = runBlockingTest {
        verify(tvRepository).fetchTvCredits(TV_ID)
        verifyFetchingTvDetails()
        verifyNoMoreInteractions(tvRepository)
    }

    private fun verifyFetchingTvDetails() = runBlockingTest {
        assert(vm.tvDetails.value == TV_DETAILS)
        assert(vm.error.value == null)
        assert(vm.exception.value == null)
        assert(vm.loading.value == false)

        verify(tvRepository).fetchDetails(TV_ID)
    }

    companion object {
        private const val TV_ID = 456
        private const val ERROR_CODE = 888
        private val TV_DETAILS = UITvDetails(5, 25, "Overview", "Status", "01.01.1970", null, null, emptyList())
        private val TV_CREDITS = UIEntertainmentCredits(emptyList(), emptyList())
        private val EXCEPTION = Exception("Mock test exception")
    }
}
