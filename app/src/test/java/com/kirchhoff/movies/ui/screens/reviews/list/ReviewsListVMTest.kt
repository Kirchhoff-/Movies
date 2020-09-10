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

@Suppress("EXPERIMENTAL_API_USAGE", "UNCHECKED_CAST")
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
        verifyFirstPageErrorState(MOVIE_REVIEWS)
    }

    @Test
    fun `verify exception state after fetching first page for movie reviews`() = runBlockingTest {
        verifyFirstPageExceptionState(MOVIE_REVIEWS)
    }

    @Test
    fun `verify success state after fetching first page for movie reviews`() = runBlockingTest {
        verifyFirstPageSuccessState(MOVIE_REVIEWS)
    }

    @Test
    fun `verify error state after fetching random page for movie reviews`() = runBlockingTest {
        verifyRandomPageErrorState(MOVIE_REVIEWS)
    }

    @Test
    fun `verify exception state after fetching random page for movie reviews`() = runBlockingTest {
        verifyRandomPageExceptionState(MOVIE_REVIEWS)
    }

    @Test
    fun `verify success state after fetching random page for movie reviews`() = runBlockingTest {
        verifyRandomPageSuccessState(MOVIE_REVIEWS)
    }

    @Test
    fun `verify success first loading and error after paginated loading for movie reviews`() = runBlockingTest {
        verifyPaginatedErrorState(MOVIE_REVIEWS)
    }

    @Test
    fun `verify success first loading and exception after paginated loading for movie reviews`() = runBlockingTest {
        verifyPaginatedExceptionState(MOVIE_REVIEWS)
    }

    @Test
    fun `verify success first loading and success paginated loading after for movie reviews`() = runBlockingTest {
        verifyPaginatedSuccessState(MOVIE_REVIEWS)
    }

    @Test
    fun `verify error state after fetching first page for tv reviews`() = runBlockingTest {
        verifyFirstPageErrorState(TV_REVIEWS)
    }

    @Test
    fun `verify exception state after fetching first page for tv reviews`() = runBlockingTest {
        verifyFirstPageExceptionState(TV_REVIEWS)
    }

    @Test
    fun `verify success state after fetching first page for tv reviews`() = runBlockingTest {
        verifyFirstPageSuccessState(TV_REVIEWS)
    }

    @Test
    fun `verify error state after fetching random page for tv reviews`() = runBlockingTest {
        verifyRandomPageErrorState(TV_REVIEWS)
    }

    @Test
    fun `verify exception state after fetching random page for tv reviews`() = runBlockingTest {
        verifyRandomPageExceptionState(TV_REVIEWS)
    }

    @Test
    fun `verify success state after fetching random page for tv reviews`() = runBlockingTest {
        verifyRandomPageSuccessState(TV_REVIEWS)
    }

    @Test
    fun `verify success first loading and error after paginated loading for tv reviews`() = runBlockingTest {
        verifyPaginatedErrorState(TV_REVIEWS)
    }

    @Test
    fun `verify success first loading and exception after paginated loading for tv reviews`() = runBlockingTest {
        verifyPaginatedExceptionState(TV_REVIEWS)
    }

    @Test
    fun `verify success first loading and success paginated loading after for tv reviews`() = runBlockingTest {
        verifyPaginatedSuccessState(TV_REVIEWS)
    }

    private fun verifyFirstPageErrorState(reviewType: ReviewType) = runBlockingTest {
        mockRepository(reviewType, FIRST_PAGE, error = ERROR)

        vm.fetchData(FIRST_PAGE)

        vm.verifyFirstPageErrorState(ERROR)
        verifyRepository(reviewType, FIRST_PAGE)
    }

    private fun verifyFirstPageExceptionState(reviewType: ReviewType) = runBlockingTest {
        mockRepository(reviewType, FIRST_PAGE, exception = EXCEPTION)

        vm.fetchData(FIRST_PAGE)

        vm.verifyFirstPageExceptionState(EXCEPTION)
        verifyRepository(reviewType, FIRST_PAGE)
    }

    private fun verifyFirstPageSuccessState(reviewType: ReviewType) = runBlockingTest {
        mockRepository(reviewType, FIRST_PAGE, data = FIRST_PAGE_ANSWER)

        vm.fetchData(FIRST_PAGE)

        vm.verifyFirstPageSuccessState(FIRST_PAGE_ANSWER)
        verifyRepository(reviewType, FIRST_PAGE)
    }

    private fun verifyRandomPageErrorState(reviewType: ReviewType) = runBlockingTest {
        mockRepository(reviewType, RANDOM_PAGE, error = ERROR)

        vm.fetchData(RANDOM_PAGE)

        vm.verifyRandomPageErrorState(ERROR)
        verifyRepository(reviewType, RANDOM_PAGE)
    }

    private fun verifyRandomPageExceptionState(reviewType: ReviewType) = runBlockingTest {
        mockRepository(reviewType, RANDOM_PAGE, exception = EXCEPTION)

        vm.fetchData(RANDOM_PAGE)

        vm.verifyRandomPageExceptionState(EXCEPTION)
        verifyRepository(reviewType, RANDOM_PAGE)
    }

    private fun verifyRandomPageSuccessState(reviewType: ReviewType) = runBlockingTest {
        mockRepository(reviewType, RANDOM_PAGE, data = RANDOM_PAGE_ANSWER)

        vm.fetchData(RANDOM_PAGE)

        vm.verifyRandomPageSuccessState(RANDOM_PAGE_ANSWER)
        verifyRepository(reviewType, RANDOM_PAGE)
    }

    private fun verifyPaginatedErrorState(reviewType: ReviewType) = runBlockingTest {
        mockRepository(reviewType, FIRST_PAGE, data = FIRST_PAGE_ANSWER)
        mockRepository(reviewType, RANDOM_PAGE, error = ERROR)

        vm.fetchData(FIRST_PAGE)

        vm.verifyFirstPageSuccessState(FIRST_PAGE_ANSWER)
        verifyRepository(reviewType, FIRST_PAGE)

        vm.fetchData(RANDOM_PAGE)

        vm.verifyPaginatedErrorState(FIRST_PAGE_ANSWER, ERROR)
        verifyRepository(reviewType, RANDOM_PAGE)
    }

    private fun verifyPaginatedExceptionState(reviewType: ReviewType) = runBlockingTest {
        mockRepository(reviewType, FIRST_PAGE, data = FIRST_PAGE_ANSWER)
        mockRepository(reviewType, RANDOM_PAGE, exception = EXCEPTION)

        vm.fetchData(FIRST_PAGE)

        vm.verifyFirstPageSuccessState(FIRST_PAGE_ANSWER)
        verifyRepository(reviewType, FIRST_PAGE)

        vm.fetchData(RANDOM_PAGE)

        vm.verifyPaginatedExceptionState(FIRST_PAGE_ANSWER, EXCEPTION)
        verifyRepository(reviewType, RANDOM_PAGE)
    }

    private fun verifyPaginatedSuccessState(reviewType: ReviewType) = runBlockingTest {
        mockRepository(reviewType, FIRST_PAGE, data = FIRST_PAGE_ANSWER)
        mockRepository(reviewType, RANDOM_PAGE, data = RANDOM_PAGE_ANSWER)

        vm.fetchData(FIRST_PAGE)

        vm.verifyFirstPageSuccessState(FIRST_PAGE_ANSWER)
        verifyRepository(reviewType, FIRST_PAGE)

        vm.fetchData(RANDOM_PAGE)

        vm.verifyPaginatedSuccessState(RANDOM_PAGE_ANSWER)
        verifyRepository(reviewType, RANDOM_PAGE)
    }

    private fun mockRepository(
        reviewType: ReviewType,
        page: Int,
        error: Result.Error<*>? = null,
        exception: Result.Exception<*>? = null,
        data: Result.Success<*>? = null
    ) = runBlockingTest {
        val result = when {
            error != null -> ERROR
            exception != null -> EXCEPTION
            data != null -> data
            else -> error("Cant mock tv repository with such data")
        }

        when (reviewType) {
            ReviewType.MOVIE -> {
                setupVM(MOVIE_REVIEWS)
                `when`(
                    movieRepository.fetchReviewsList(
                        DATA_ID,
                        page
                    )
                ).thenReturn(result as Result<UIPaginated<UIReview>>?)
            }
            ReviewType.TV -> {
                setupVM(TV_REVIEWS)
                `when`(
                    tvRepository.fetchReviewsList(
                        DATA_ID,
                        page
                    )
                ).thenReturn(result as Result<UIPaginated<UIReview>>?)
            }
        }
    }

    private fun verifyRepository(reviewType: ReviewType, page: Int) = runBlockingTest {
        when (reviewType) {
            ReviewType.MOVIE -> {
                verify(movieRepository).fetchReviewsList(DATA_ID, page)
                verifyNoInteractions(tvRepository)
                verifyNoMoreInteractions(movieRepository)
            }
            ReviewType.TV -> {
                verify(tvRepository).fetchReviewsList(DATA_ID, page)
                verifyNoInteractions(movieRepository)
                verifyNoMoreInteractions(tvRepository)
            }
        }
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
