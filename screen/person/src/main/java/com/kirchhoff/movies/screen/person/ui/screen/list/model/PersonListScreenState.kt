package com.kirchhoff.movies.screen.person.ui.screen.list.model

import com.kirchhoff.movies.core.data.UIPerson

internal data class PersonListScreenState(
    val personList: List<UIPerson>,
    val errorMessage: String,
    val loadingVisible: Boolean,
    val paginationVisible: Boolean
)
