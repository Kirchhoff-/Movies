@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.screen.tvshow.ui.screen.details.ui.credits

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.creditsview.R

@Composable
internal fun TvShowDetailsCreditsUI(
    credits: UIEntertainmentCredits,
    onItemClick: (UIEntertainmentPerson) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val castCredits = credits.cast.orEmpty()
        if (castCredits.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(R.string.cast_credits),
                color = colorResource(com.kirchhoff.movies.core.R.color.text_main),
                fontSize = 16.sp
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(
                    count = castCredits.size,
                    itemContent = {
                        TvShowDetailsCreditsItemUI(
                            credit = castCredits[it],
                            onItemClick = onItemClick
                        )
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        val crewCredits = credits.crew.orEmpty()
        if (crewCredits.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(R.string.crew_credits),
                color = colorResource(com.kirchhoff.movies.core.R.color.text_main),
                fontSize = 16.sp
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(
                    count = crewCredits.size,
                    itemContent = {
                        TvShowDetailsCreditsItemUI(
                            credit = crewCredits[it],
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
private fun TvShowDetailsCreditsUIPreview() {
    TvShowDetailsCreditsUI(
        credits = UIEntertainmentCredits(
            cast = emptyList(),
            crew = emptyList()
        ),
        onItemClick = {}
    )
}
