package com.kirchhoff.movies.screen.person.ui.screen.details.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.kirchhoff.movies.screen.person.data.UIPersonDetails
import com.kirchhoff.movies.screen.person.ui.screen.details.model.PersonDetailsScreenState

@Composable
internal fun PersonDetailsUI(
    screenState: PersonDetailsScreenState
) {
    Box(modifier = Modifier.background(Color.Red))
}

@Preview
@Composable
internal fun PersonDetailsUIPreview() {
    PersonDetailsUI(
        screenState = PersonDetailsScreenState(
            details = UIPersonDetails(
                birthday = "",
                placeOfBirth = "",
                biography = "",
                alsoKnownAs = emptyList()
            )
        )
    )
}
