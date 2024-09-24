package com.kirchhoff.movies.screen.credits.ui.screen.crew.viewmodel

import androidx.lifecycle.ViewModel
import com.kirchhoff.movies.core.data.MovieId
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.credits.R
import com.kirchhoff.movies.screen.credits.ui.screen.crew.model.CreditsCrewScreenState
import com.kirchhoff.movies.screen.credits.ui.screen.crew.usecase.ICreditsCrewUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal class CreditsCrewViewModel(
    private val movieId: MovieId,
    private val creditsCrewUseCase: ICreditsCrewUseCase
) : ViewModel() {

    val screenState: MutableStateFlow<CreditsCrewScreenState> = MutableStateFlow(CreditsCrewScreenState.Default)

    private var expandableItems: MutableSet<String> = mutableSetOf()

    init {
        screenState.update {
            it.copy(
                creators = creditsCrewUseCase.createCrewList(movieId, expandableItems),
                title = StringValue.IdText(R.string.credits_all_crew)
            )
        }
    }

    fun onItemClicked(job: String) {
        if (expandableItems.contains(job)) expandableItems.remove(job) else expandableItems.add(job)

        screenState.update {
            it.copy(creators = creditsCrewUseCase.createCrewList(movieId, expandableItems))
        }
    }
}
