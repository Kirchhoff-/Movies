@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.screen.movie.ui.screen.details.ui.companies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirchhoff.movies.core.ui.resources.Colors
import com.kirchhoff.movies.screen.movie.R
import com.kirchhoff.movies.screen.movie.data.UIProductionCompany

@Composable
internal fun MovieDetailsProductionCompaniesUI(
    companies: List<UIProductionCompany>,
    onItemClick: (UIProductionCompany) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(R.string.movie_production_companies),
            color = Colors.TextMain,
            fontSize = 16.sp
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(
                count = companies.size,
                itemContent = {
                    MovieDetailsProductionCompaniesItemUI(
                        company = companies[it],
                        onItemClick = onItemClick
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun MovieDetailsProductionCompaniesUIPreview() {
    MovieDetailsProductionCompaniesUI(
        companies = emptyList(),
        onItemClick = {}
    )
}
