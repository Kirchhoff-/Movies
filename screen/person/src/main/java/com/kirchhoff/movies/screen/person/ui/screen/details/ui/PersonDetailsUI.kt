package com.kirchhoff.movies.screen.person.ui.screen.details.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.kirchhoff.movies.screen.person.R
import com.kirchhoff.movies.screen.person.data.UIPersonDetails
import com.kirchhoff.movies.screen.person.ui.screen.details.model.PersonDetailsScreenState

@Composable
internal fun PersonDetailsUI(
    screenState: PersonDetailsScreenState
) {
    Column {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = screenState.name,
            color = colorResource(com.kirchhoff.movies.core.R.color.text_main),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
internal fun PersonDetailsUIPreview() {
    PersonDetailsUI(
        screenState = PersonDetailsScreenState(
            name = "",
            details = UIPersonDetails(
                birthday = "",
                placeOfBirth = "",
                biography = "",
                alsoKnownAs = emptyList()
            )
        )
    )
}
