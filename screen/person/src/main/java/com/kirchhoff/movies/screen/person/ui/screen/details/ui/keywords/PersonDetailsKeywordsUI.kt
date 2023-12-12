@file:SuppressWarnings("MagicNumber")
package com.kirchhoff.movies.screen.person.ui.screen.details.ui.keywords

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalLayoutApi
@Composable
internal fun PersonDetailsKeywordsUI(keywords: List<String>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp)
            .shadow(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        FlowRow(modifier = Modifier.padding(8.dp)) {
            keywords.forEach { keyword ->
                PersonDetailsKeywordsItemUI(text = keyword)
            }
        }
    }
}

@ExperimentalLayoutApi
@Preview
@Composable
private fun PersonDetailsKeywordsUIPreview() {
    PersonDetailsKeywordsUI(keywords = emptyList())
}
