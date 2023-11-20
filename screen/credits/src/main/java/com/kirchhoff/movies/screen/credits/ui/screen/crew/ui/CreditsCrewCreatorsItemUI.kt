package com.kirchhoff.movies.screen.credits.ui.screen.crew.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun CreditsCrewCreatorsItemUI() {
    Box(
        modifier = Modifier
            .height(128.dp)
            .fillMaxWidth()
            .background(Color.Red)
    )
}

@Preview
@Composable
internal fun CreditsCrewCreatorsItemUIPreview() {
    CreditsCrewCreatorsItemUI()
}
