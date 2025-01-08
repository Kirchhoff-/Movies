package com.kirchhoff.movies.screen.person.ui.screen.list.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kirchhoff.movies.core.data.ui.UIPerson
import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH
import com.kirchhoff.movies.core.ui.resources.Colors

@SuppressWarnings("MagicNumber")
@Composable
internal fun PersonListItemUI(
    person: UIPerson,
    onPersonClick: (UIPerson) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = { onPersonClick.invoke(person) }
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape),
            model = BASE_POSTER_PATH + person.profilePath,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(com.kirchhoff.movies.core.R.drawable.ic_account_circle),
            error = painterResource(com.kirchhoff.movies.core.R.drawable.ic_account_circle),
            contentDescription = ""
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = person.name,
            color = Colors.TextMain,
            fontSize = 16.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@SuppressWarnings("MagicNumber")
@Preview
@Composable
private fun PersonListItemUIPreview() {
    PersonListItemUI(
        person = UIPerson.Default,
        onPersonClick = {}
    )
}
