package com.kirchhoff.movies.screen.credits.ui.screen.crew.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirchhoff.movies.screen.credits.R

@Composable
internal fun CreditsCrewListJobUI(jobText: String) {
    Row {
        Text(
            text = jobText,
            fontSize = 16.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painter = painterResource(R.drawable.ic_chevron_right),
            colorFilter = ColorFilter.tint(Color.Black),
            contentDescription = ""
        )
    }
}

@Preview
@Composable
internal fun CreditsCrewListJobUIPreview() {
    CreditsCrewListJobUI("Creator")
}
