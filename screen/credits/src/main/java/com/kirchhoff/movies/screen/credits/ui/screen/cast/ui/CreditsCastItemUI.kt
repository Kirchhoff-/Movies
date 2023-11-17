package com.kirchhoff.movies.screen.credits.ui.screen.cast.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kirchhoff.movies.core.data.UIEntertainmentPerson
import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH
import kotlin.random.Random

@Composable
internal fun CreditsCastItemUI(actor: UIEntertainmentPerson.Actor) {
    Row(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxHeight()
                .width(60.dp),
            model = BASE_POSTER_PATH + actor.profilePath,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(com.kirchhoff.movies.core.R.drawable.ic_empty_avatar),
            error = painterResource(com.kirchhoff.movies.core.R.drawable.ic_empty_avatar),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = actor.name)
            Text(text = actor.character.orEmpty())
        }
    }
}

@Preview
@Composable
internal fun CreditsCastItemUIPreview() {
    CreditsCastItemUI(
        UIEntertainmentPerson.Actor(
            id = Random.nextInt(),
            name = "Ethan",
            profilePath = null,
            character = "Joker"
        )
    )
}
