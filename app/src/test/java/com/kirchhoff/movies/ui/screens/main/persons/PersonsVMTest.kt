package com.kirchhoff.movies.ui.screens.main.persons

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kirchhoff.movies.data.ui.core.UIPaginated
import com.kirchhoff.movies.data.ui.main.UIPerson
import com.kirchhoff.movies.repository.Result
import com.kirchhoff.movies.repository.person.IPersonsRepository
import com.kirchhoff.movies.ui.screens.core.verifyFirstPageErrorState
import com.kirchhoff.movies.ui.screens.core.verifyFirstPageExceptionState
import com.kirchhoff.movies.ui.screens.core.verifyFirstPageSuccessState
import com.kirchhoff.movies.ui.screens.core.verifyPaginatedErrorState
import com.kirchhoff.movies.ui.screens.core.verifyPaginatedExceptionState
import com.kirchhoff.movies.ui.screens.core.verifyPaginatedSuccessState
import com.kirchhoff.movies.ui.screens.core.verifyRandomPageErrorState
import com.kirchhoff.movies.ui.screens.core.verifyRandomPageExceptionState
import com.kirchhoff.movies.ui.screens.core.verifyRandomPageSuccessState
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
class PersonsVMTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val personsRepository = mock(IPersonsRepository::class.java)

    private lateinit var vm: PersonsVM

    @Before
    fun setUp() {
        vm = PersonsVM(personsRepository)
    }

    @Test
    fun `verify error state after fetching first page`() = runBlockingTest {
        `when`(personsRepository.fetchPopularPersons(FIRST_PAGE)).thenReturn(ERROR)

        vm.fetchData(FIRST_PAGE)

        vm.verifyFirstPageErrorState(ERROR)
        verifyRepository(FIRST_PAGE)
    }

    @Test
    fun `verify exception state after fetching first page`() = runBlockingTest {
        `when`(personsRepository.fetchPopularPersons(FIRST_PAGE)).thenReturn(EXCEPTION)

        vm.fetchData(FIRST_PAGE)

        vm.verifyFirstPageExceptionState(EXCEPTION)
        verifyRepository(FIRST_PAGE)
    }

    @Test
    fun `verify success state after fetching first page`() = runBlockingTest {
        `when`(personsRepository.fetchPopularPersons(FIRST_PAGE)).thenReturn(FIRST_PAGE_PERSON_ANSWER)

        vm.fetchData(FIRST_PAGE)

        vm.verifyFirstPageSuccessState(FIRST_PAGE_PERSON_ANSWER)
        verifyRepository(FIRST_PAGE)
    }

    @Test
    fun `verify error state after fetching random page`() = runBlockingTest {
        `when`(personsRepository.fetchPopularPersons(RANDOM_PAGE)).thenReturn(ERROR)

        vm.fetchData(RANDOM_PAGE)

        vm.verifyRandomPageErrorState(ERROR)
        verifyRepository(RANDOM_PAGE)
    }

    @Test
    fun `verify exception state after fetching random page`() = runBlockingTest {
        `when`(personsRepository.fetchPopularPersons(RANDOM_PAGE)).thenReturn(EXCEPTION)

        vm.fetchData(RANDOM_PAGE)

        vm.verifyRandomPageExceptionState(EXCEPTION)
        verifyRepository(RANDOM_PAGE)
    }

    @Test
    fun `verify success state after fetching random page`() = runBlockingTest {
        `when`(personsRepository.fetchPopularPersons(RANDOM_PAGE)).thenReturn(RANDOM_PAGE_PERSON_ANSWER)

        vm.fetchData(RANDOM_PAGE)

        vm.verifyRandomPageSuccessState(RANDOM_PAGE_PERSON_ANSWER)
        verifyRepository(RANDOM_PAGE)
    }

    @Test
    fun `verify success first loading and error after paginated loading`() = runBlockingTest {
        `when`(personsRepository.fetchPopularPersons(FIRST_PAGE)).thenReturn(FIRST_PAGE_PERSON_ANSWER)
        `when`(personsRepository.fetchPopularPersons(RANDOM_PAGE)).thenReturn(ERROR)

        vm.fetchData(FIRST_PAGE)

        vm.verifyFirstPageSuccessState(FIRST_PAGE_PERSON_ANSWER)
        verify(personsRepository).fetchPopularPersons(FIRST_PAGE)

        vm.fetchData(RANDOM_PAGE)

        vm.verifyPaginatedErrorState(FIRST_PAGE_PERSON_ANSWER, ERROR)
        verify(personsRepository).fetchPopularPersons(RANDOM_PAGE)

        verifyNoMoreInteractions(personsRepository)
    }

    @Test
    fun `verify success first loading and exception after paginated loading`() = runBlockingTest {
        `when`(personsRepository.fetchPopularPersons(FIRST_PAGE)).thenReturn(FIRST_PAGE_PERSON_ANSWER)
        `when`(personsRepository.fetchPopularPersons(RANDOM_PAGE)).thenReturn(EXCEPTION)

        vm.fetchData(FIRST_PAGE)

        vm.verifyFirstPageSuccessState(FIRST_PAGE_PERSON_ANSWER)
        verify(personsRepository).fetchPopularPersons(FIRST_PAGE)

        vm.fetchData(RANDOM_PAGE)

        vm.verifyPaginatedExceptionState(FIRST_PAGE_PERSON_ANSWER, EXCEPTION)
        verify(personsRepository).fetchPopularPersons(RANDOM_PAGE)

        verifyNoMoreInteractions(personsRepository)
    }

    @Test
    fun `verify success first loading and success paginated loading after`() = runBlockingTest {
        `when`(personsRepository.fetchPopularPersons(FIRST_PAGE)).thenReturn(FIRST_PAGE_PERSON_ANSWER)
        `when`(personsRepository.fetchPopularPersons(RANDOM_PAGE)).thenReturn(RANDOM_PAGE_PERSON_ANSWER)

        vm.fetchData(FIRST_PAGE)

        vm.verifyFirstPageSuccessState(FIRST_PAGE_PERSON_ANSWER)
        verify(personsRepository).fetchPopularPersons(FIRST_PAGE)

        vm.fetchData(RANDOM_PAGE)

        vm.verifyPaginatedSuccessState(RANDOM_PAGE_PERSON_ANSWER)
        verify(personsRepository).fetchPopularPersons(RANDOM_PAGE)

        verifyNoMoreInteractions(personsRepository)
    }

    private fun verifyRepository(page: Int) = runBlockingTest {
        verify(personsRepository).fetchPopularPersons(page)
        verifyNoMoreInteractions(personsRepository)
    }

    companion object {
        private val ERROR = Result.Error<UIPaginated<UIPerson>>(333)
        private val EXCEPTION = Result.Exception<UIPaginated<UIPerson>>(Exception("Persons mock exception"))
        private val RANDOM_PAGE = Random.nextInt(1, 100)
        private val FIRST_PAGE_PERSON_ANSWER = Result.Success(UIPaginated(1, listOf(UIPerson(1, "First person", null)), Int.MAX_VALUE))
        private val RANDOM_PAGE_PERSON_ANSWER = Result.Success(UIPaginated(1, listOf(UIPerson(2, "Random person", null)), Int.MAX_VALUE))
        private const val FIRST_PAGE = 1
    }
}
