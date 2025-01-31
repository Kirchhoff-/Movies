@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.screen.tvshow.ui.screen.details.ui.keywords

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun TvShowDetailsKeywordsUI(keywords: List<String>) {
    FlowRow(modifier = Modifier.padding(start = 12.dp)) {
        keywords.forEach { keyword ->
            TvShowDetailsKeywordsItemUI(text = keyword)
        }
    }
}

@Preview
@Composable
private fun TvShowDetailsKeywordsUIPreview() {
    TvShowDetailsKeywordsUI(keywords = emptyList())
}
