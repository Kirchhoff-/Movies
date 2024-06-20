package com.kirchhoff.movies.screen.credits.ui.screen.crew.ui.items.job

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirchhoff.movies.core.ui.resources.Colors
import com.kirchhoff.movies.screen.credits.R

@SuppressWarnings("MagicNumber")
@Composable
internal fun CreditsCrewJobItemUI(
    jobText: String,
    isExpanded: Boolean,
    onItemClick: (String) -> Unit
) {
    val animatedRotation by animateFloatAsState(
        targetValue = if (isExpanded) 90f else 0f,
        animationSpec = tween(
            durationMillis = 300,
            easing = LinearOutSlowInEasing
        ),
        label = "rotation Animation"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Colors.LightGray)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = { onItemClick.invoke(jobText) }
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.wrapContentSize(),
            text = jobText,
            fontSize = 16.sp,
            color = Colors.Black
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier
                .wrapContentSize()
                .rotate(animatedRotation),
            painter = painterResource(R.drawable.ic_chevron_right),
            colorFilter = ColorFilter.tint(Colors.Black),
            contentDescription = ""
        )
    }
}

@Preview
@Composable
private fun CreditsCrewListJobUIPreview() {
    CreditsCrewJobItemUI(
        jobText = "Creator",
        isExpanded = false,
        onItemClick = {}
    )
}
