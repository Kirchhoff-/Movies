package com.kirchhoff.movies.screen.credits.ui.screen.crew.ui.items.persons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH
import com.kirchhoff.movies.screen.credits.ui.screen.crew.model.CreditsCrewListPersonItem

@SuppressWarnings("MagicNumber")
@Composable
internal fun CreditsCrewPersonItemUI(
    person: CreditsCrewListPersonItem
) {
    Card(
        modifier = Modifier
            .height(250.dp)
            .width(150.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp),
                model = BASE_POSTER_PATH + person.profilePath,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(com.kirchhoff.movies.core.R.drawable.ic_empty_avatar),
                error = painterResource(com.kirchhoff.movies.core.R.drawable.ic_empty_avatar),
                contentDescription = ""
            )
            Divider(color = Color.Gray)
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(),
                text = person.name,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = colorResource(com.kirchhoff.movies.core.R.color.text_main)
            )
        }
    }
}

@Preview
@Composable
private fun CreditsCrewPersonItemUIPreview() {
    CreditsCrewListPersonItem(
        name = "Some name",
        profilePath = null
    )
}
