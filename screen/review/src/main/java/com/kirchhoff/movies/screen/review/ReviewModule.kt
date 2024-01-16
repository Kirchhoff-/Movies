package com.kirchhoff.movies.screen.review

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.screen.review.mapper.IReviewListMapper
import com.kirchhoff.movies.screen.review.mapper.ReviewListMapper
import com.kirchhoff.movies.screen.review.network.ReviewService
import com.kirchhoff.movies.screen.review.repository.IReviewRepository
import com.kirchhoff.movies.screen.review.repository.ReviewRepository
import com.kirchhoff.movies.screen.review.router.IReviewRouter
import com.kirchhoff.movies.screen.review.router.ReviewRouter
import com.kirchhoff.movies.screen.review.ui.screen.list.model.ReviewsListArgs
import com.kirchhoff.movies.screen.review.ui.screen.list.viewmodel.ReviewsListViewModel
import com.kirchhoff.movies.screen.review.usecase.IReviewUseCase
import com.kirchhoff.movies.screen.review.usecase.ReviewUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

internal val reviewModule = module {
    single { get<Retrofit>().create(ReviewService::class.java) }

    single<IReviewRouter> { (activity: AppCompatActivity) ->
        ReviewRouter(activity)
    }

    single<IReviewListMapper> { ReviewListMapper() }

    single<IReviewRepository> {
        ReviewRepository(reviewService = get())
    }

    single<IReviewUseCase> {
        ReviewUseCase(
            reviewRepository = get(),
            reviewMapper = get(),
            movieStorage = get(),
            tvShowStorage = get()
        )
    }

    viewModel { (args: ReviewsListArgs) ->
        ReviewsListViewModel(
            args = args,
            useCase = get()
        )
    }
}
