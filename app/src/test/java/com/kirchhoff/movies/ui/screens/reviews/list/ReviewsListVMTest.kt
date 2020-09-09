package com.kirchhoff.movies.ui.screens.reviews.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kirchhoff.movies.data.ui.core.UIPaginated
import com.kirchhoff.movies.data.ui.details.review.UIReview
import com.kirchhoff.movies.repository.Result
import com.kirchhoff.movies.repository.movie.IMovieRepository
import com.kirchhoff.movies.repository.tv.ITvRepository
import com.kirchhoff.movies.ui.screens.core.verifyFirstPageErrorState
import com.kirchhoff.movies.ui.screens.core.verifyFirstPageExceptionState
import com.kirchhoff.movies.ui.screens.core.verifyFirstPageSuccessState
import com.kirchhoff.movies.ui.screens.core.verifyPaginatedErrorState
import com.kirchhoff.movies.ui.screens.core.verifyPaginatedExceptionState
import com.kirchhoff.movies.ui.screens.core.verifyPaginatedSuccessState
import com.kirchhoff.movies.ui.screens.core.verifyRandomPageErrorState
import com.kirchhoff.movies.ui.screens.core.verifyRandomPageExceptionState
import com.kirchhoff.movies.ui.screens.core.verifyRandomPageSuccessState
import com.kirchhoff.movies.ui.screens.reviews.ReviewType
import com.kirchhoff.movies.utils.CoroutineRule
import kotlin.random.Random
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoInteractions
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.junit.MockitoJUnitRunner

@Suppress("EXPERIMENTAL_API_USAGE")
@RunWith(MockitoJUnitRunner::class)
class ReviewsListVMTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val movieRepository = mock(IMovieRepository::class.java)
    private val tvRepository = mock(ITvRepository::class.java)

    private lateinit var vm: ReviewsListVM

    private fun setupVM(reviewType: ReviewType) {
        vm = ReviewsListVM(DATA_ID, reviewType, movieRepository, tvRepository)
    }

    @Test
    fun `verify error state after fetching first page for movie reviews`() = runBlockingTest {
        setupVM(MOVIE_REVIEWS)
        `when`(movieRepository.fetchReviewsList(DATA_ID, FIRST_PAGE)).thenReturn(ERROR)

        vm.fetchData(FIRST_PAGE)

        vm.verifyFirstPageErrorState(ERROR)
        verifyMovieRepository(FIRST_PAGE)
    }

    @Test
    fun `verify exception state after fetching first page for movie reviews`() = runBlockingTest {
        setupVM(MOVIE_REVIEWS)
        `when`(movieRepository.fetchReviewsList(DATA_ID, FIRST_PAGE)).thenReturn(EXCEPTION)

        vm.fetchData(FIRST_PAGE)

        vm.verifyFirstPageExceptionState(EXCEPTION)
        verifyMovieRepository(FIRST_PAGE)
    }

    @Test
    fun `verify success state after fetching first page for movie reviews`() = runBlockingTest {
        setupVM(MOVIE_REVIEWS)
        `when`(movieRepository.fetchReviewsList(DATA_ID, FIRST_PAGE)).thenReturn(FIRST_PAGE_ANSWER)

        vm.fetchData(FIRST_PAGE)

        vm.verifyFirstPageSuccessState(FIRST_PAGE_ANSWER)
        verifyMovieRepository(FIRST_PAGE)
    }

    @Test
    fun `verify error state after fetching random page for movie reviews`() = runBlockingTest {
        setupVM(MOVIE_REVIEWS)
        `when`(movieRepository.fetchReviewsList(DATA_ID, RANDOM_PAGE)).thenReturn(ERROR)

        vm.fetchData(RANDOM_PAGE)

        vm.verifyRandomPageErrorState(ERROR)
        verifyMovieRepository(RANDOM_PAGE)
    }

    @Test
    fun `verify exception state after fetching random page for movie reviews`() = runBlockingTest {
        setupVM(MOVIE_REVIEWS)
        `when`(movieRepository.fetchReviewsList(DATA_ID, RANDOM_PAGE)).thenReturn(EXCEPTION)

        vm.fetchData(RANDOM_PAGE)

        vm.verifyRandomPageExceptionState(EXCEPTION)
        verifyMovieRepository(RANDOM_PAGE)
    }

    @Test
    fun `verify success state after fetching random page for movie reviews`() = runBlockingTest {
        setupVM(MOVIE_REVIEWS)
        `when`(movieRepository.fetchReviewsList(DATA_ID, RANDOM_PAGE)).thenReturn(RANDOM_PAGE_ANSWER)

        vm.fetchData(RANDOM_PAGE)

        vm.verifyRandomPageSuccessState(RANDOM_PAGE_ANSWER)
        verifyMovieRepository(RANDOM_PAGE)
    }

    @Test
    fun `verify success first loading and error after paginated loading for movie reviews`() = runBlockingTest {
        setupVM(MOVIE_REVIEWS)
        `when`(movieRepository.fetchReviewsList(DATA_ID, FIRST_PAGE)).thenReturn(FIRST_PAGE_ANSWER)
        `when`(movieRepository.fetchReviewsList(DATA_ID, RANDOM_PAGE)).thenReturn(ERROR)

        vm.fetchData(FIRST_PAGE)

        vm.verifyFirstPageSuccessState(FIRST_PAGE_ANSWER)
        verify(movieRepository).fetchReviewsList(DATA_ID, FIRST_PAGE)

        vm.fetchData(RANDOM_PAGE)

        vm.verifyPaginatedErrorState(FIRST_PAGE_ANSWER, ERROR)
        verify(movieRepository).fetchReviewsList(DATA_ID, RANDOM_PAGE)

        verifyNoInteractions(tvRepository)
        verifyNoMoreInteractions(movieRepository)
    }

    @Test
    fun `verify success first loading and exception after paginated loading for movie reviews`() = runBlockingTest {
        setupVM(MOVIE_REVIEWS)
        `when`(movieRepository.fetchReviewsList(DATA_ID, FIRST_PAGE)).thenReturn(FIRST_PAGE_ANSWER)
        `when`(movieRepository.fetchReviewsList(DATA_ID, RANDOM_PAGE)).thenReturn(EXCEPTION)

        vm.fetchData(FIRST_PAGE)

        vm.verifyFirstPageSuccessState(FIRST_PAGE_ANSWER)
        verify(movieRepository).fetchReviewsList(DATA_ID, FIRST_PAGE)

        vm.fetchData(RANDOM_PAGE)

        vm.verifyPaginatedExceptionState(FIRST_PAGE_ANSWER, EXCEPTION)
        verify(movieRepository).fetchReviewsList(DATA_ID, RANDOM_PAGE)

        verifyNoInteractions(tvRepository)
        verifyNoMoreInteractions(movieRepository)
    }

    @Test
    fun `verify success first loading and success paginated loading after for movie reviews`() = runBlockingTest {
        setupVM(MOVIE_REVIEWS)
        `when`(movieRepository.fetchReviewsList(DATA_ID, FIRST_PAGE)).thenReturn(FIRST_PAGE_ANSWER)
        `when`(movieRepository.fetchReviewsList(DATA_ID, RANDOM_PAGE)).thenReturn(RANDOM_PAGE_ANSWER)

        vm.fetchData(FIRST_PAGE)

        vm.verifyFirstPageSuccessState(FIRST_PAGE_ANSWER)
        verify(movieRepository).fetchReviewsList(DATA_ID, FIRST_PAGE)

        vm.fetchData(RANDOM_PAGE)

        vm.verifyPaginatedSuccessState(RANDOM_PAGE_ANSWER)
        verify(movieRepository).fetchReviewsList(DATA_ID, RANDOM_PAGE)

        verifyNoInteractions(tvRepository)
        verifyNoMoreInteractions(movieRepository)
    }

    @Test
    fun `verify error state after fetching first page for tv reviews`() = runBlockingTest {
        setupVM(TV_REVIEWS)
        `when`(tvRepository.fetchReviewsList(DATA_ID, FIRST_PAGE)).thenReturn(ERROR)

        vm.fetchData(FIRST_PAGE)

        vm.verifyFirstPageErrorState(ERROR)
        verifyTvRepository(FIRST_PAGE)
    }

    @Test
    fun `verify exception state after fetching first page for tv reviews`() = runBlockingTest {
        setupVM(TV_REVIEWS)
        `when`(tvRepository.fetchReviewsList(DATA_ID, FIRST_PAGE)).thenReturn(EXCEPTION)

        vm.fetchData(FIRST_PAGE)

        vm.verifyFirstPageExceptionState(EXCEPTION)
        verifyTvRepository(FIRST_PAGE)
    }

    @Test
    fun `verify success state after fetching first page for tv reviews`() = runBlockingTest {
        setupVM(TV_REVIEWS)
        `when`(tvRepository.fetchReviewsList(DATA_ID, FIRST_PAGE)).thenReturn(FIRST_PAGE_ANSWER)

        vm.fetchData(FIRST_PAGE)

        vm.verifyFirstPageSuccessState(FIRST_PAGE_ANSWER)
        verifyTvRepository(FIRST_PAGE)
    }

    @Test
    fun `verify error state after fetching random page for tv reviews`() = runBlockingTest {
        setupVM(TV_REVIEWS)
        `when`(tvRepository.fetchReviewsList(DATA_ID, RANDOM_PAGE)).thenReturn(ERROR)

        vm.fetchData(RANDOM_PAGE)

        vm.verifyRandomPageErrorState(ERROR)
        verifyTvRepository(RANDOM_PAGE)
    }

    @Test
    fun `verify exception state after fetching random page for tv reviews`() = runBlockingTest {
        setupVM(TV_REVIEWS)
        `when`(tvRepository.fetchReviewsList(DATA_ID, RANDOM_PAGE)).thenReturn(EXCEPTION)

        vm.fetchData(RANDOM_PAGE)

        vm.verifyRandomPageExceptionState(EXCEPTION)
        verifyTvRepository(RANDOM_PAGE)
    }

    @Test
    fun `verify success state after fetching random page for tv reviews`() = runBlockingTest {
        setupVM(TV_REVIEWS)
        `when`(tvRepository.fetchReviewsList(DATA_ID, RANDOM_PAGE)).thenReturn(RANDOM_PAGE_ANSWER)

        vm.fetchData(RANDOM_PAGE)

        vm.verifyRandomPageSuccessState(RANDOM_PAGE_ANSWER)
        verifyTvRepository(RANDOM_PAGE)
    }

    @Test
    fun `verify success first loading and error after paginated loading for tv reviews`() = runBlockingTest {
        setupVM(TV_REVIEWS)
        `when`(tvRepository.fetchReviewsList(DATA_ID, FIRST_PAGE)).thenReturn(FIRST_PAGE_ANSWER)
        `when`(tvRepository.fetchReviewsList(DATA_ID, RANDOM_PAGE)).thenReturn(ERROR)

        vm.fetchData(FIRST_PAGE)

        vm.verifyFirstPageSuccessState(FIRST_PAGE_ANSWER)
        verify(tvRepository).fetchReviewsList(DATA_ID, FIRST_PAGE)

        vm.fetchData(RANDOM_PAGE)

        vm.verifyPaginatedErrorState(FIRST_PAGE_ANSWER, ERROR)
        verify(tvRepository).fetchReviewsList(DATA_ID, RANDOM_PAGE)

        verifyNoInteractions(movieRepository)
        verifyNoMoreInteractions(tvRepository)
    }

    @Test
    fun `verify success first loading and exception after paginated loading for tv reviews`() = runBlockingTest {
        setupVM(TV_REVIEWS)
        `when`(tvRepository.fetchReviewsList(DATA_ID, FIRST_PAGE)).thenReturn(FIRST_PAGE_ANSWER)
        `when`(tvRepository.fetchReviewsList(DATA_ID, RANDOM_PAGE)).thenReturn(EXCEPTION)

        vm.fetchData(FIRST_PAGE)

        vm.verifyFirstPageSuccessState(FIRST_PAGE_ANSWER)
        verify(tvRepository).fetchReviewsList(DATA_ID, FIRST_PAGE)

        vm.fetchData(RANDOM_PAGE)

        vm.verifyPaginatedExceptionState(FIRST_PAGE_ANSWER, EXCEPTION)
        verify(tvRepository).fetchReviewsList(DATA_ID, RANDOM_PAGE)

        verifyNoInteractions(movieRepository)
        verifyNoMoreInteractions(tvRepository)
    }

    @Test
    fun `verify success first loading and success paginated loading after for tv reviews`() = runBlockingTest {
        setupVM(TV_REVIEWS)
        `when`(tvRepository.fetchReviewsList(DATA_ID, FIRST_PAGE)).thenReturn(FIRST_PAGE_ANSWER)
        `when`(tvRepository.fetchReviewsList(DATA_ID, RANDOM_PAGE)).thenReturn(RANDOM_PAGE_ANSWER)

        vm.fetchData(FIRST_PAGE)

        vm.verifyFirstPageSuccessState(FIRST_PAGE_ANSWER)
        verify(tvRepository).fetchReviewsList(DATA_ID, FIRST_PAGE)

        vm.fetchData(RANDOM_PAGE)

        vm.verifyPaginatedSuccessState(RANDOM_PAGE_ANSWER)
        verify(tvRepository).fetchReviewsList(DATA_ID, RANDOM_PAGE)

        verifyNoInteractions(movieRepository)
        verifyNoMoreInteractions(tvRepository)
    }

    private fun verifyMovieRepository(page: Int) = runBlockingTest {
        verify(movieRepository).fetchReviewsList(DATA_ID, page)
        verifyNoInteractions(tvRepository)
        verifyNoMoreInteractions(movieRepository)
    }

    private fun verifyTvRepository(page: Int) = runBlockingTest {
        verify(tvRepository).fetchReviewsList(DATA_ID, page)
        verifyNoInteractions(movieRepository)
        verifyNoMoreInteractions(tvRepository)
    }

    companion object {
        private const val DATA_ID = 1000
        private const val FIRST_PAGE = 1
        private val MOVIE_REVIEWS = ReviewType.MOVIE
        private val TV_REVIEWS = ReviewType.TV
        private val RANDOM_PAGE = Random.nextInt(1, 100)
        private val ERROR = Result.Error<UIPaginated<UIReview>>(777)
        private val EXCEPTION = Result.Exception<UIPaginated<UIReview>>(Exception("Reviews exception"))
        private val FIRST_PAGE_ANSWER = Result.Success(UIPaginated(1, listOf(UIReview("First author name", "First review content")), Int.MAX_VALUE))
        private val RANDOM_PAGE_ANSWER = Result.Success(UIPaginated(2, listOf(UIReview("Random author name", "Random review content")), Int.MAX_VALUE))
    }
}
