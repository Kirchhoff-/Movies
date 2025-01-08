@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.screen.movie.ui.screen.details.ui.credits

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirchhoff.movies.core.data.ui.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.ui.UIEntertainmentPerson
import com.kirchhoff.movies.core.ui.resources.Colors
import com.kirchhoff.movies.screen.movie.R

@SuppressWarnings("LongMethod")
@Composable
internal fun MovieDetailsCreditsUI(
    credits: UIEntertainmentCredits,
    onItemClick: (UIEntertainmentPerson) -> Unit,
    onCastSeeAllClick: () -> Unit,
    onCrewSeeAllClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val castCredits = credits.cast.orEmpty()
        if (castCredits.isNotEmpty()) {
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = stringResource(R.string.movie_cast_credits),
                    color = Colors.TextMain,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true),
                        onClick = { onCastSeeAllClick.invoke() }
                    ),
                    text = stringResource(R.string.movie_see_all),
                    color = Colors.Link,
                    fontSize = 16.sp
                )
            }
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(
                    count = castCredits.size,
                    itemContent = {
                        MovieDetailsCreditsItemUI(
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
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = stringResource(R.string.movie_crew_credits),
                    color = Colors.TextMain,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true),
                        onClick = { onCrewSeeAllClick.invoke() }
                    ),
                    text = stringResource(R.string.movie_see_all),
                    color = Colors.Link,
                    fontSize = 16.sp
                )
            }
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(
                    count = crewCredits.size,
                    itemContent = {
                        MovieDetailsCreditsItemUI(
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
private fun MovieDetailsCreditsUIPreview() {
    MovieDetailsCreditsUI(
        credits = UIEntertainmentCredits.Default,
        onItemClick = {},
        onCastSeeAllClick = {},
        onCrewSeeAllClick = {}
    )
}
