package com.kirchhoff.movies.screen.person.ui.screen.list.model

import com.kirchhoff.movies.core.data.ui.UIPerson

internal data class PersonListScreenState(
    val personList: List<UIPerson>,
    val errorMessage: String,
    val loadingVisible: Boolean,
    val paginationVisible: Boolean
) {
    companion object {
        val Default = PersonListScreenState(
            personList = emptyList(),
            errorMessage = "",
            loadingVisible = false,
            paginationVisible = false
        )
    }
}
