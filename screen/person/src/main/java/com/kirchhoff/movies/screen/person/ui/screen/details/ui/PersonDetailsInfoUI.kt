package com.kirchhoff.movies.screen.person.ui.screen.details.ui

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kirchhoff.movies.screen.person.R
import com.kirchhoff.movies.screen.person.data.UIPersonDetails

@Composable
internal fun PersonDetailsInfoUI(details: UIPersonDetails) {
    Card(
        modifier = Modifier.shadow(10.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.background(Color.Red)
        ) {
            Text(
                text = stringResource(R.string.person_born)
            )
            Text(
                text = details.birthday.orEmpty()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.person_birthplace)
            )
            Text(
                text = details.placeOfBirth.orEmpty()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.person_bio)
            )
            Text(
                text = details.biography
            )
        }
    }
}
