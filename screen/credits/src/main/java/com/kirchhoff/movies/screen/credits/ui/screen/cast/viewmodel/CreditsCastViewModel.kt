package com.kirchhoff.movies.screen.credits.ui.screen.cast.viewmodel

import androidx.lifecycle.ViewModel
import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.credits.R
import com.kirchhoff.movies.screen.credits.ui.screen.cast.model.CreditsCastScreenState
import com.kirchhoff.movies.screen.credits.ui.screen.cast.usecase.ICreditsCastUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal class CreditsCastViewModel(
    movieId: MovieId,
    creditsCastUseCase: ICreditsCastUseCase
) : ViewModel() {

    val screenState: MutableStateFlow<CreditsCastScreenState> = MutableStateFlow(CreditsCastScreenState.Default)

    init {
        screenState.update {
            it.copy(
                actors = creditsCastUseCase.actorsList(movieId),
                title = StringValue.IdText(R.string.credits_all_cast)
            )
        }
    }
}
