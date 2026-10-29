package com.kirchhoff.movies.screen.review.ui.screen.list.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.kirchhoff.movies.core.ui.resources.Colors
import com.kirchhoff.movies.screen.review.R
import com.kirchhoff.movies.screen.review.data.ReviewUIInfo

@SuppressWarnings("MagicNumber")
@Composable
internal fun ReviewsAuthorSheetUI(
    author: ReviewUIInfo.Author,
    onOpenProfileClick: (String) -> Unit
) {
    val title = author.getName() ?: stringResource(R.string.review_author)
    val showUsernameBelowName = author.displayName != null && author.username != null
    val showProfileButton = author.profileUrl != null

    Column(
        modifier = Modifier.fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .navigationBarsPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = author.avatar,
            modifier = Modifier.size(96.dp).clip(CircleShape),
            placeholder = painterResource(com.kirchhoff.movies.core.R.drawable.ic_account_circle),
            error = painterResource(com.kirchhoff.movies.core.R.drawable.ic_account_circle),
            fallback = painterResource(com.kirchhoff.movies.core.R.drawable.ic_account_circle),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(
            text = title,
            color = Colors.TextMain,
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
        if (showUsernameBelowName) {
            Text(
                text = stringResource(R.string.review_author_username_format, author.username),
                color = Colors.TextHint,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        if (showProfileButton) {
            Button(
                onClick = { onOpenProfileClick(author.profileUrl) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Colors.Primary,
                    contentColor = Colors.White
                ),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(stringResource(R.string.review_author_open_profile))
            }
        }
    }
}
