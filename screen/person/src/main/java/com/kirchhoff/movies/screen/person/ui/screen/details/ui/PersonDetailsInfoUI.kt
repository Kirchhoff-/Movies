package com.kirchhoff.movies.screen.person.ui.screen.details.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirchhoff.movies.screen.person.R
import com.kirchhoff.movies.screen.person.data.UIPersonDetails

@Composable
internal fun PersonDetailsInfoUI(details: UIPersonDetails) {
    Card(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 16.dp)
            .shadow(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = stringResource(R.string.person_born),
                fontSize = 14.sp,
                color = colorResource(com.kirchhoff.movies.core.R.color.text_main)
            )
            Text(
                text = details.birthday.orEmpty(),
                fontSize = 14.sp,
                color = colorResource(com.kirchhoff.movies.core.R.color.text_hint)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.person_birthplace),
                fontSize = 14.sp,
                color = colorResource(com.kirchhoff.movies.core.R.color.text_main)
            )
            Text(
                text = details.placeOfBirth.orEmpty(),
                fontSize = 14.sp,
                color = colorResource(com.kirchhoff.movies.core.R.color.text_hint)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.person_bio),
                fontSize = 14.sp,
                color = colorResource(com.kirchhoff.movies.core.R.color.text_main)
            )
            Text(
                text = details.biography,
                fontSize = 14.sp,
                color = colorResource(com.kirchhoff.movies.core.R.color.text_hint)
            )
        }
    }
}
