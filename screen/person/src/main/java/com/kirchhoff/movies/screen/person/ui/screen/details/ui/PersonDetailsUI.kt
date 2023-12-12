@file:SuppressWarnings("MagicNumber")
package com.kirchhoff.movies.screen.person.ui.screen.details.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirchhoff.movies.core.ui.compose.MoviesToolbar
import com.kirchhoff.movies.screen.person.data.UIPersonCredit
import com.kirchhoff.movies.screen.person.data.UIPersonCredits
import com.kirchhoff.movies.screen.person.data.UIPersonDetails
import com.kirchhoff.movies.screen.person.ui.screen.details.model.PersonDetailsScreenState
import com.kirchhoff.movies.screen.person.ui.screen.details.ui.credits.PersonDetailsCreditsUI
import com.kirchhoff.movies.screen.person.ui.screen.details.ui.images.PersonDetailsImagesUI
import com.kirchhoff.movies.screen.person.ui.screen.details.ui.info.PersonDetailsInfoUI
import com.kirchhoff.movies.screen.person.ui.screen.details.ui.keywords.PersonDetailsKeywordsUI

@ExperimentalLayoutApi
@Composable
internal fun PersonDetailsUI(
    screenState: PersonDetailsScreenState,
    onCreditItemClick: (UIPersonCredit) -> Unit,
    onImageClick: (Int) -> Unit,
    onLocationClick: () -> Unit,
    onBackPressed: () -> Unit
) {
    Column {
        MoviesToolbar(title = screenState.title) {
            onBackPressed.invoke()
        }

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            PersonDetailsImagesUI(
                images = screenState.images,
                onItemClick = onImageClick
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = screenState.name,
                color = colorResource(com.kirchhoff.movies.core.R.color.text_main),
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            PersonDetailsInfoUI(
                details = screenState.details,
                onLocationClick = onLocationClick
            )
            if (screenState.details.alsoKnownAs?.isEmpty() == false) {
                Spacer(modifier = Modifier.height(16.dp))
                PersonDetailsKeywordsUI(keywords = screenState.details.alsoKnownAs)
            }
            if (
                screenState.credits.cast?.isNotEmpty() == true ||
                screenState.credits.crew?.isNotEmpty() == true
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                PersonDetailsCreditsUI(
                    credits = screenState.credits,
                    onItemClick = onCreditItemClick
                )
            }
        }
    }
}

@ExperimentalLayoutApi
@Preview
@Composable
internal fun PersonDetailsUIPreview() {
    PersonDetailsUI(
        screenState = PersonDetailsScreenState(
            name = "",
            title = "",
            details = UIPersonDetails(
                birthday = "",
                placeOfBirth = "",
                biography = "",
                alsoKnownAs = emptyList()
            ),
            credits = UIPersonCredits(
                cast = emptyList(),
                crew = emptyList()
            ),
            images = emptyList()
        ),
        onCreditItemClick = {},
        onImageClick = {},
        onLocationClick = {},
        onBackPressed = {}
    )
}
