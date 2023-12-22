@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.screen.person.ui.screen.details.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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

        when {
            screenState.isLoading -> ShowLoading()
            screenState.errorMessage.isNotEmpty() -> ShowError(screenState = screenState)
            else -> ShowUI(
                screenState = screenState,
                onCreditItemClick = onCreditItemClick,
                onImageClick = onImageClick,
                onLocationClick = onLocationClick
            )
        }
    }
}

@Composable
private fun ShowLoading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ShowError(screenState: PersonDetailsScreenState) {
    val context = LocalContext.current
    var errorMessage by rememberSaveable { mutableStateOf("") }

    if (screenState.errorMessage.isNotEmpty() && errorMessage.isEmpty()) {
        errorMessage = screenState.errorMessage
        SideEffect {
            Toast.makeText(context, screenState.errorMessage, Toast.LENGTH_LONG).show()
        }
    }
}

@ExperimentalLayoutApi
@Composable
private fun ShowUI(
    screenState: PersonDetailsScreenState,
    onCreditItemClick: (UIPersonCredit) -> Unit,
    onImageClick: (Int) -> Unit,
    onLocationClick: () -> Unit
) {
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

@ExperimentalLayoutApi
@Preview
@Composable
private fun PersonDetailsUIPreview() {
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
            images = emptyList(),
            isLoading = false,
            errorMessage = ""
        ),
        onCreditItemClick = {},
        onImageClick = {},
        onLocationClick = {},
        onBackPressed = {}
    )
}
