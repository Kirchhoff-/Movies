package com.kirchhoff.movies.screen.person.ui.screen.details.ui.credits

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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
        modifier = Modifier.shadow(10.dp),
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
                model = BASE_POSTER_PATH + credit.posterPath,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.ic_empty_movie),
                error = painterResource(R.drawable.ic_empty_movie),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = credit.title,
                    fontSize = 16.sp,
                    color = colorResource(R.color.text_main)
                )
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = colorResource(R.color.text_hint)
                )
            }
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
