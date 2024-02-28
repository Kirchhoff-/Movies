package com.kirchhoff.movies.screen.credits.ui.screen.cast.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.credits.R
import com.kirchhoff.movies.screen.credits.ui.screen.cast.model.CreditsCastScreenState
import com.kirchhoff.movies.screen.credits.ui.screen.cast.usecase.ICreditsCastUseCase

internal class CreditsCastViewModel(
    movieId: MovieId,
    creditsCastUseCase: ICreditsCastUseCase
) : ViewModel() {

    val screenState: MutableLiveData<CreditsCastScreenState> = MutableLiveData()

    init {
        screenState.value = CreditsCastScreenState(
            actors = creditsCastUseCase.actorsList(movieId),
            title = StringValue.IdText(R.string.credits_all_cast)
        )
    }
}
