package com.kirchhoff.movies.screen.credits.ui.screen.crew.ui.items.persons

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kirchhoff.movies.screen.credits.ui.screen.crew.model.CreditsCrewListPersonItem

@SuppressWarnings("MagicNumber")
@Composable
internal fun CreditsCrewPersonsItemUI(
    persons: List<CreditsCrewListPersonItem>,
    isExpanded: Boolean
) {
    val expandTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(300)
        ) + fadeIn(
            animationSpec = tween(300)
        )
    }

    val collapseTransition = remember {
        shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(300)
        ) + fadeOut(
            animationSpec = tween(300)
        )
    }

    AnimatedVisibility(
        visible = isExpanded,
        enter = expandTransition,
        exit = collapseTransition
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(all = 16.dp)
        ) {
            items(
                count = persons.size,
                itemContent = {
                    CreditsCrewPersonItemUI(person = persons[it])
                }
            )
        }
    }
}

@Preview
@Composable
private fun CreditsCrewPersonsItemUIPreview() {
    CreditsCrewPersonsItemUI(
        persons = emptyList(),
        isExpanded = false
    )
}
