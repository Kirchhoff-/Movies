package com.kirchhoff.movies.screen.credits.ui.screen.crew.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirchhoff.movies.screen.credits.R

@Composable
internal fun CreditsCrewListJobUI(
    jobText: String,
    onItemClick: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .height(48.dp)
            .background(Color.LightGray)
            .padding(horizontal = 16.dp)
            .clickable { onItemClick.invoke(jobText) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.wrapContentSize(),
            text = jobText,
            fontSize = 16.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier.wrapContentSize(),
            painter = painterResource(R.drawable.ic_chevron_right),
            colorFilter = ColorFilter.tint(Color.Black),
            contentDescription = ""
        )
    }
}

@Preview
@Composable
internal fun CreditsCrewListJobUIPreview() {
    CreditsCrewListJobUI(
        jobText = "Creator",
        onItemClick = {}
    )
}
