@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.screen.movie.ui.screen.details.ui.credits

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kirchhoff.movies.core.R
import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH

@Composable
internal fun MovieDetailsCreditsItemUI(
    credit: UIEntertainmentPerson,
    onItemClick: (UIEntertainmentPerson) -> Unit
) {
    val description = when (credit) {
        is UIEntertainmentPerson.Actor -> credit.character
        is UIEntertainmentPerson.Creator -> credit.job
    }

    Card(
        modifier = Modifier
            .height(250.dp)
            .width(150.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = { onItemClick.invoke(credit) }
            ),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp),
                model = BASE_POSTER_PATH + credit.profilePath,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.ic_empty_avatar),
                error = painterResource(R.drawable.ic_empty_avatar),
                contentDescription = ""
            )
            Divider(color = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier.padding(horizontal = 4.dp),
                text = credit.name,
                fontSize = 16.sp,
                color = colorResource(R.color.text_main),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.padding(horizontal = 4.dp),
                text = description.orEmpty(),
                fontSize = 14.sp,
                color = colorResource(R.color.text_hint),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
private fun MovieDetailsCreditsItemUIPreview() {
    MovieDetailsCreditsItemUI(
        credit = UIEntertainmentPerson.Actor(
            id = 1,
            name = "Name",
            profilePath = "",
            character = ""
        ),
        onItemClick = {}
    )
}
