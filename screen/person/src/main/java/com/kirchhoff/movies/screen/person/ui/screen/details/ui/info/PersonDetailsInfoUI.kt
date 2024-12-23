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
import com.kirchhoff.movies.screen.person.ui.screen.details.model.UIPersonDetails

@Composable
internal fun PersonDetailsInfoUI(
    details: UIPersonDetails,
    onLocationClick: () -> Unit
) {
    val isBornVisible = !details.birthday.isNullOrEmpty()
    val isPlaceOfBirthVisible = !details.placeOfBirth.isNullOrEmpty()
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
                    Text(
                        style = TextStyles.Info,
                        text = stringResource(R.string.person_born)
                    )
                    Text(
                        style = supportTextStyle,
                        text = details.birthday.orEmpty()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                if (isPlaceOfBirthVisible) {
                    Text(
                        style = TextStyles.Info,
                        text = stringResource(R.string.person_birthplace)
                    )
                    Text(
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = true),
                            onClick = { onLocationClick.invoke() }
                        ),
                        style = supportTextStyle,
                        textDecoration = TextDecoration.Underline,
                        text = details.placeOfBirth.orEmpty()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                if (isBiographyVisible) {
                    Text(
                        style = TextStyles.Info,
                        text = stringResource(R.string.person_bio)
                    )
                    Text(
                        style = supportTextStyle,
                        text = details.biography
                    )
                }
            }
        }
    }
}

private val supportTextStyle: TextStyle = TextStyle(
    fontSize = 14.sp,
    color = Colors.Gray
)

@Preview
@Composable
private fun PersonDetailsInfoUIPreview() {
    PersonDetailsInfoUI(
        details = UIPersonDetails.Default,
        onLocationClick = {}
    )
}
