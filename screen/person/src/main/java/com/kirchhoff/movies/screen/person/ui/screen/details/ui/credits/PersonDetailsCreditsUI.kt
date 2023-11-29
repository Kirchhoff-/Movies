package com.kirchhoff.movies.screen.person.ui.screen.details.ui.credits

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirchhoff.movies.screen.person.R
import com.kirchhoff.movies.screen.person.data.UIPersonCredit
import com.kirchhoff.movies.screen.person.data.UIPersonCredits

@Composable
internal fun PersonDetailsCreditsUI(
    credits: UIPersonCredits,
    onItemClick: (UIPersonCredit) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (credits.cast?.isNotEmpty() == true) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(com.kirchhoff.movies.creditsview.R.string.cast_credits),
                color = colorResource(com.kirchhoff.movies.core.R.color.text_main),
                fontSize = 16.sp
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(
                    count = credits.cast.size,
                    itemContent = {
                        PersonDetailsCreditsItemUI(
                            credit = credits.cast[it],
                            onItemClick = onItemClick
                        )
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (credits.crew?.isNotEmpty() == true) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(com.kirchhoff.movies.creditsview.R.string.crew_credits),
                color = colorResource(com.kirchhoff.movies.core.R.color.text_main),
                fontSize = 16.sp
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(
                    count = credits.crew.size,
                    itemContent = {
                        PersonDetailsCreditsItemUI(
                            credit = credits.crew[it],
                            onItemClick = onItemClick
                        )
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
        credits = UIPersonCredits(
            cast = emptyList(),
            crew = emptyList()
        ),
        onItemClick = {}
    )
}
