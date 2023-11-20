package com.kirchhoff.movies.screen.credits.ui.screen.crew.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.credits.R
import com.kirchhoff.movies.screen.credits.ui.screen.crew.model.CreditsCrewScreenState
import com.kirchhoff.movies.screen.credits.ui.screen.crew.usecase.ICreditsCrewUseCase

internal class CreditsCrewViewModel(
    private val creditsCrewUseCase: ICreditsCrewUseCase,
    private val creators: List<UIEntertainmentPerson.Creator>
) : ViewModel() {

    val screenState: MutableLiveData<CreditsCrewScreenState> = MutableLiveData()

    private var expandableItems: MutableSet<String> = mutableSetOf()

    init {
        screenState.value = CreditsCrewScreenState(
            creators = creditsCrewUseCase.createCrewList(creators, expandableItems),
            title = StringValue.IdText(R.string.credits_all_crew)
        )
    }

    fun onItemClicked(job: String) {
        if (expandableItems.contains(job)) expandableItems.remove(job) else expandableItems.add(job)

        screenState.value = screenState.value?.copy(
            creators = creditsCrewUseCase.createCrewList(creators, expandableItems)
        )
    }
}
