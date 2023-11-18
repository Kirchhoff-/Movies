package com.kirchhoff.movies.screen.credits.ui.screen.crew.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.credits.ui.screen.crew.model.CreditsCrewScreenState

internal class CreditsCrewViewModel(
    creators: List<UIEntertainmentPerson.Creator>
) : ViewModel() {

    val screenState: MutableLiveData<CreditsCrewScreenState> = MutableLiveData()

    init {
        screenState.value = CreditsCrewScreenState(
            creators = emptyList(),
            title = StringValue.Empty
        )
    }
}
