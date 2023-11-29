package com.kirchhoff.movies.screen.person.ui.screen.details.ui.keywords

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalLayoutApi
@Composable
internal fun PersonDetailsKeywordsItemUI(text: String) {
    Text(
       modifier = Modifier
           .background(
               color = Color.Black,
               shape = RoundedCornerShape(8.dp)
           )
           .padding(8.dp),
        color = Color.Red,
        fontSize = 14.sp,
        text = text
    )
}

@ExperimentalLayoutApi
@Composable
private fun PersonDetailsKeywordsItemUIPreview() {
    PersonDetailsKeywordsItemUI("Keyword")
}
