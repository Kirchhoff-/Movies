package com.kirchhoff.movies.screen.review

import com.kirchhoff.movies.screen.review.mapper.IReviewListMapper
import com.kirchhoff.movies.screen.review.mapper.ReviewListMapper
import com.kirchhoff.movies.screen.review.network.ReviewService
import com.kirchhoff.movies.screen.review.repository.IReviewRepository
import com.kirchhoff.movies.screen.review.repository.ReviewRepository
import com.kirchhoff.movies.screen.review.ui.screen.ReviewType
import com.kirchhoff.movies.screen.review.ui.screen.list.ReviewsListVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

internal val reviewModule = module {
    single { get<Retrofit>().create(ReviewService::class.java) }

    single<IReviewListMapper> { ReviewListMapper() }

    single<IReviewRepository> {
        ReviewRepository(
            reviewService = get(),
            reviewMapper = get()
        )
    }

    viewModel { (dataId: Int, reviewType: ReviewType) ->
        ReviewsListVM(
            dataId,
            reviewType,
            reviewRepository = get()
        )
    }
}
