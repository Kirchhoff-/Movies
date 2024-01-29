@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.voteview

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VoteViewComposable(
    voteAverage: Float?,
    voteCount: Int?
) {
    if (voteAverage != null && voteCount != null) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                style = textStyle,
                text = stringResource(R.string.vote_view_rating_format, voteAverage)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                style = textStyle,
                text = voteCount.toString()
            )
        }
    }
}

private val textStyle: TextStyle = TextStyle(
    fontSize = 14.sp,
    color = Color.Black
)

@Preview
@Composable
private fun VoteViewComposablePreview() {
    VoteViewComposable(
        voteAverage = 8.0f,
        voteCount = 2
    )
}
