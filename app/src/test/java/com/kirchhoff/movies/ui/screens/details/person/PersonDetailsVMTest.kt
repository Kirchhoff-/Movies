package com.kirchhoff.movies.ui.screens.details.person

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kirchhoff.movies.core.repository.Result
import com.kirchhoff.movies.data.ui.details.person.UIPersonCredits
import com.kirchhoff.movies.data.ui.details.person.UIPersonDetails
import com.kirchhoff.movies.repository.person.IPersonsRepository
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
class PersonDetailsVMTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val personRepository = mock(IPersonsRepository::class.java)

    private lateinit var vm: PersonDetailsVM

    @Before
    fun setUp() {
        vm = PersonDetailsVM(personRepository)
    }

    @Test
    fun `verify error state after fetching person details`() = runBlockingTest {
        val error = Result.Error<UIPersonDetails>(ERROR_CODE)
        `when`(personRepository.fetchPersonDetail(PERSON_ID)).thenReturn(error)

        vm.loadPersonDetails(PERSON_ID)

        assert(vm.personDetails.value == null)
        assert(vm.error.value == error.toString())
        assert(vm.exception.value == null)
        assert(vm.personCredits.value == null)
        assert(vm.loading.value == false)
        verify(personRepository).fetchPersonDetail(PERSON_ID)
        verifyNoMoreInteractions(personRepository)
    }

    @Test
    fun `verify exception state after fetching person details`() = runBlockingTest {
        val exception = Result.Exception<UIPersonDetails>(EXCEPTION)
        `when`(personRepository.fetchPersonDetail(PERSON_ID)).thenReturn(exception)

        vm.loadPersonDetails(PERSON_ID)

        assert(vm.personDetails.value == null)
        assert(vm.error.value == null)
        assert(vm.exception.value == exception.toString())
        assert(vm.personCredits.value == null)
        assert(vm.loading.value == false)
        verify(personRepository).fetchPersonDetail(PERSON_ID)
        verifyNoMoreInteractions(personRepository)
    }

    @Test
    fun `verify success state after fetching person details`() = runBlockingTest {
        mockSuccessPersonDetails()

        vm.loadPersonDetails(PERSON_ID)

        verifyRequests()
    }

    @Test
    fun `verify error state after fetching person credits`() = runBlockingTest {
        mockSuccessPersonDetails()
        val error = Result.Error<UIPersonCredits>(ERROR_CODE)
        `when`(personRepository.fetchPersonCredits(PERSON_ID)).thenReturn(error)

        vm.loadPersonDetails(PERSON_ID)

        assert(vm.personCredits.value == null)
        verifyRequests()
    }

    @Test
    fun `verify exception state after fetching person credits`() = runBlockingTest {
        mockSuccessPersonDetails()
        val exception = Result.Exception<UIPersonCredits>(EXCEPTION)
        `when`(personRepository.fetchPersonCredits(PERSON_ID)).thenReturn(exception)

        vm.loadPersonDetails(PERSON_ID)

        assert(vm.personCredits.value == null)
        verifyRequests()
    }

    @Test
    fun `verify success state after fetching person credits`() = runBlockingTest {
        mockSuccessPersonDetails()
        val personCredits = Result.Success(PERSON_CREDITS)
        `when`(personRepository.fetchPersonCredits(PERSON_ID)).thenReturn(personCredits)

        vm.loadPersonDetails(PERSON_ID)

        assert(vm.personCredits.value == PERSON_CREDITS)
        verifyRequests()
    }

    private fun verifyRequests() = runBlockingTest {
        verify(personRepository).fetchPersonCredits(PERSON_ID)
        verifyFetchingPersonDetails()
        verifyNoMoreInteractions(personRepository)
    }

    private fun mockSuccessPersonDetails() = runBlockingTest {
        val personDetails = Result.Success(PERSON_DETAILS)
        `when`(personRepository.fetchPersonDetail(PERSON_ID)).thenReturn(personDetails)
    }

    private fun verifyFetchingPersonDetails() = runBlockingTest {
        assert(vm.personDetails.value == PERSON_DETAILS)
        assert(vm.error.value == null)
        assert(vm.exception.value == null)
        assert(vm.loading.value == false)

        verify(personRepository).fetchPersonDetail(PERSON_ID)
    }

    companion object {
        private const val PERSON_ID = 123
        private const val ERROR_CODE = 999
        private val PERSON_DETAILS = UIPersonDetails("1.1.1970", "Earth", "Biography", emptyList())
        private val PERSON_CREDITS = UIPersonCredits(emptyList(), emptyList())
        private val EXCEPTION = Exception("Mock test exception")
    }
}
