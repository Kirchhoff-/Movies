@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.screen.person.ui.screen.details.ui.credits

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kirchhoff.movies.core.R
import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH
import com.kirchhoff.movies.core.ui.resources.Colors
import com.kirchhoff.movies.screen.person.ui.screen.details.model.UIPersonCredit

@Composable
internal fun PersonDetailsCreditsItemUI(
    credit: UIPersonCredit,
    onItemClick: (UIPersonCredit) -> Unit
) {
    val description = when (credit) {
        is UIPersonCredit.Actor -> credit.character.orEmpty()
        is UIPersonCredit.Creator -> credit.job
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
                model = BASE_POSTER_PATH + credit.posterPath,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.ic_empty_movie),
                error = painterResource(R.drawable.ic_empty_movie),
                contentDescription = ""
            )
            Divider(color = Colors.Gray)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier.padding(horizontal = 4.dp),
                text = credit.title,
                fontSize = 16.sp,
                color = Colors.TextMain,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.padding(horizontal = 4.dp),
                text = description,
                fontSize = 14.sp,
                color = Colors.TextHint,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
private fun PersonDetailsCreditsItemUIPreview() {
    PersonDetailsCreditsItemUI(
        credit = UIPersonCredit.Actor.Default,
        onItemClick = {}
    )
}
