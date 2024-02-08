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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.creditsview.R

@SuppressWarnings("LongMethod")
@Composable
internal fun MovieDetailsCreditsUI(
    credits: UIEntertainmentCredits,
    onItemClick: (UIEntertainmentPerson) -> Unit,
    onCastSeeAllClick: (List<UIEntertainmentPerson.Actor>) -> Unit,
    onCrewSeeAllClick: (List<UIEntertainmentPerson.Creator>) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val castCredits = credits.cast.orEmpty()
        if (castCredits.isNotEmpty()) {
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = stringResource(R.string.cast_credits),
                    color = colorResource(com.kirchhoff.movies.core.R.color.text_main),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true),
                        onClick = { onCastSeeAllClick.invoke(castCredits) }
                    ),
                    text = stringResource(R.string.see_all),
                    color = colorResource(com.kirchhoff.movies.core.R.color.link_color),
                    fontSize = 16.sp
                )
            }
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(
                    count = DISPLAYING_ITEM_COUNT,
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
                    text = stringResource(R.string.crew_credits),
                    color = colorResource(com.kirchhoff.movies.core.R.color.text_main),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true),
                        onClick = { onCrewSeeAllClick.invoke(crewCredits) }
                    ),
                    text = stringResource(R.string.see_all),
                    color = colorResource(com.kirchhoff.movies.core.R.color.link_color),
                    fontSize = 16.sp
                )
            }
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(
                    count = DISPLAYING_ITEM_COUNT,
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

private const val DISPLAYING_ITEM_COUNT = 10

@Preview
@Composable
private fun MovieDetailsCreditsUIPreview() {
    MovieDetailsCreditsUI(
        credits = UIEntertainmentCredits(
            cast = emptyList(),
            crew = emptyList()
        ),
        onItemClick = {},
        onCastSeeAllClick = {},
        onCrewSeeAllClick = {}
    )
}
