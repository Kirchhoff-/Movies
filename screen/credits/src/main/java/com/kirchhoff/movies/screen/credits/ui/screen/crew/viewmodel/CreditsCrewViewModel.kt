package com.kirchhoff.movies.screen.credits.ui.screen.crew.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.credits.ui.screen.crew.model.CreditsCrewScreenState
import com.kirchhoff.movies.screen.credits.ui.screen.crew.usecase.ICreditsCrewUseCase

internal class CreditsCrewViewModel(
    creditsCrewUseCase: ICreditsCrewUseCase,
    creators: List<UIEntertainmentPerson.Creator>
) : ViewModel() {

    val screenState: MutableLiveData<CreditsCrewScreenState> = MutableLiveData()

    init {
        screenState.value = CreditsCrewScreenState(
            creators = creditsCrewUseCase.createCrewList(creators),
            title = StringValue.Empty
        )
    }
}
