package com.kirchhoff.movies.screen.credits.ui.screen.cast.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kirchhoff.movies.core.data.ui.UIEntertainmentPerson
import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH
import com.kirchhoff.movies.core.ui.resources.Colors

@SuppressWarnings("MagicNumber")
@Composable
internal fun CreditsCastItemUI(
    actor: UIEntertainmentPerson.Actor,
    onPersonCreditsItemClick: (UIEntertainmentPerson) -> Unit
) {
    Card(
        modifier = Modifier.shadow(10.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = { onPersonCreditsItemClick.invoke(actor) }
            ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(140.dp),
                model = BASE_POSTER_PATH + actor.profilePath,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(com.kirchhoff.movies.core.R.drawable.ic_empty_avatar),
                error = painterResource(com.kirchhoff.movies.core.R.drawable.ic_empty_avatar),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = actor.name,
                    fontSize = 16.sp,
                    color = Colors.TextMain
                )
                Text(
                    text = actor.character.orEmpty(),
                    fontSize = 14.sp,
                    color = Colors.TextHint
                )
            }
        }
    }
}

@Preview
@Composable
private fun CreditsCastItemUIPreview() {
    CreditsCastItemUI(
        actor = UIEntertainmentPerson.Actor.Default,
        onPersonCreditsItemClick = {}
    )
}
