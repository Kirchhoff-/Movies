package com.kirchhoff.movies.screen.person.ui.screen.details.ui.credits

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kirchhoff.movies.core.R
import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH
import com.kirchhoff.movies.screen.person.data.UIMediaType
import com.kirchhoff.movies.screen.person.data.UIPersonCredit

@Composable
internal fun PersonDetailsCreditsItemUI(credit: UIPersonCredit) {
    val description = when (credit) {
        is UIPersonCredit.Actor -> credit.character.orEmpty()
        is UIPersonCredit.Creator -> credit.job
    }

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
                model = BASE_POSTER_PATH + credit.posterPath,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.ic_empty_movie),
                error = painterResource(R.drawable.ic_empty_movie),
                contentDescription = ""
            )
            Divider(color = Color.Gray)
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(),
                text = credit.title,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = colorResource(R.color.text_main)
            )
        }
    }
}

@Preview
@Composable
private fun PersonDetailsCreditsItemUIPreview() {
    PersonDetailsCreditsItemUI(
        UIPersonCredit.Actor(
            id = 1,
            title = "Title",
            posterPath = "",
            backdropPath = "",
            UIMediaType.MOVIE,
            character = "Character"
        )
    )
}
