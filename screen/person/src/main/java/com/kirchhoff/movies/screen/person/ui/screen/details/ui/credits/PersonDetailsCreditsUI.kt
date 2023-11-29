package com.kirchhoff.movies.screen.person.ui.screen.details.ui.credits

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kirchhoff.movies.screen.person.data.UIPersonCredits

@Composable
internal fun PersonDetailsCreditsUI(credits: UIPersonCredits) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (credits.cast != null) {
            Text(text = stringResource(com.kirchhoff.movies.creditsview.R.string.cast_credits))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(
                    count = credits.cast.size,
                    itemContent = {
                        PersonDetailsCreditsItemUI(credit = credits.cast[it])
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (credits.crew != null) {
            Text(text = stringResource(com.kirchhoff.movies.creditsview.R.string.crew_credits))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(
                    count = credits.crew.size,
                    itemContent = {
                        PersonDetailsCreditsItemUI(credit = credits.crew[it])
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun PersonDetailsCreditsUIPreview() {
    PersonDetailsCreditsUI(
        UIPersonCredits(
            cast = emptyList(),
            crew = emptyList()
        )
    )
}
