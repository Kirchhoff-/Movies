@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.screen.person.ui.screen.details.ui.info

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirchhoff.movies.core.ui.resources.Colors
import com.kirchhoff.movies.core.ui.resources.TextStyles
import com.kirchhoff.movies.screen.person.R
import com.kirchhoff.movies.screen.person.ui.screen.details.model.PersonUIDetails

@Composable
internal fun PersonDetailsInfoUI(
    details: PersonUIDetails,
    onLocationClick: (String) -> Unit,
    onHomepageClick: (String) -> Unit
) {
    val isBornVisible = !details.birthday.isNullOrEmpty()
    val isPlaceOfBirthVisible = !details.placeOfBirth.isNullOrEmpty()
    val isHomepageVisible = !details.homepage.isNullOrEmpty()
    val isBiographyVisible = details.biography.isNotEmpty()

    if (isBornVisible || isPlaceOfBirthVisible || isBiographyVisible) {
        Card(
            modifier = Modifier
                .background(Colors.White)
                .padding(horizontal = 16.dp)
                .shadow(8.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                if (isBornVisible) {
                    PlaceOfBorn(details.birthday.orEmpty())
                    Spacer(modifier = Modifier.height(8.dp))
                }
                if (isPlaceOfBirthVisible) {
                    PlaceOfBirth(
                        placeOfBirth = details.placeOfBirth.orEmpty(),
                        onLocationClick = onLocationClick
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                if (isHomepageVisible) {
                    Homepage(
                        homepage = details.homepage.orEmpty(),
                        onHomepageClick = onHomepageClick
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                if (isBiographyVisible) {
                    Biography(details.biography)
                }
            }
        }
    }
}

@Composable
private fun PlaceOfBorn(birthday: String) {
    Text(
        style = TextStyles.Info,
        text = stringResource(R.string.person_born)
    )
    Text(
        style = supportTextStyle,
        text = birthday
    )
}

@Composable
private fun PlaceOfBirth(
    placeOfBirth: String,
    onLocationClick: (String) -> Unit
) {
    Text(
        style = TextStyles.Info,
        text = stringResource(R.string.person_birthplace)
    )
    Text(
        modifier = Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(bounded = true),
            onClick = { onLocationClick.invoke(placeOfBirth) }
        ),
        style = supportTextStyle,
        textDecoration = TextDecoration.Underline,
        text = placeOfBirth
    )
}

@Composable
private fun Homepage(
    homepage: String,
    onHomepageClick: (String) -> Unit
) {
    Text(
        style = TextStyles.Info,
        text = stringResource(R.string.person_homepage)
    )
    Text(
        modifier = Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(bounded = true),
            onClick = { onHomepageClick.invoke(homepage) }
        ),
        style = supportTextStyle,
        textDecoration = TextDecoration.Underline,
        text = homepage
    )
}

@Composable
private fun Biography(biography: String) {
    Text(
        style = TextStyles.Info,
        text = stringResource(R.string.person_bio)
    )
    Text(
        style = supportTextStyle,
        text = biography
    )
}

private val supportTextStyle: TextStyle = TextStyle(
    fontSize = 14.sp,
    color = Colors.Gray
)

@Preview
@Composable
private fun PersonDetailsInfoUIPreview() {
    PersonDetailsInfoUI(
        details = PersonUIDetails.Default,
        onLocationClick = {},
        onHomepageClick = {}
    )
}
