package com.kirchhoff.movies.screen.credits.ui.screen.cast.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.credits.R
import com.kirchhoff.movies.screen.credits.ui.screen.cast.model.CreditsCastScreenState

internal class CreditsCastViewModel(
    private val actors: List<UIEntertainmentPerson.Actor>
) : ViewModel() {

    val screenState: MutableLiveData<CreditsCastScreenState> = MutableLiveData()

    init {
        screenState.value = CreditsCastScreenState(
            actors = emptyList(),
            title = StringValue.IdText(R.string.credits_all_cast)
        )
    }
}
