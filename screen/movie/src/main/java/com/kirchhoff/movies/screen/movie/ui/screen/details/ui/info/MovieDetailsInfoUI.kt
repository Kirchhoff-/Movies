@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.screen.movie.ui.screen.details.ui.info

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kirchhoff.movies.core.extensions.BASE_POSTER_PATH
import com.kirchhoff.movies.core.ui.compose.TextWithIcon
import com.kirchhoff.movies.core.ui.resources.Colors
import com.kirchhoff.movies.core.ui.resources.TextStyles
import com.kirchhoff.movies.core.utils.StringValue
import com.kirchhoff.movies.screen.movie.R
import com.kirchhoff.movies.screen.movie.data.UICountry
import com.kirchhoff.movies.screen.movie.data.UIMovieInfo
import com.kirchhoff.movies.voteview.VoteViewComposable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressWarnings("LongMethod")
@Composable
internal fun MovieDetailsInfoUI(
    info: UIMovieInfo,
    posterPath: String?,
    onProductionCountryClick: (UICountry) -> Unit
) {
    val context = LocalContext.current

    val productionCountryVisible = info.productionCountries.isNotEmpty()

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    start = 16.dp,
                    end = 16.dp
                )
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(175.dp)
                    .width(120.dp),
                model = BASE_POSTER_PATH + posterPath,
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                VoteViewComposable(
                    voteAverage = info.voteAverage,
                    voteCount = info.voteCount
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextWithIcon(
                        imageVector = Icons.Filled.DateRange,
                        text = info.releaseDate.orEmpty()
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextWithIcon(
                        painter = painterResource(com.kirchhoff.movies.core.R.drawable.ic_access_time),
                        text = StringValue.IdText(
                            R.string.movie_runtime_format,
                            info.runtime.asMovieRuntime(),
                            info.runtime
                        ).asString(context)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                if (productionCountryVisible) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp)
                            .background(Colors.White)
                            .border(
                                width = 1.dp,
                                color = Colors.Black,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(bounded = true),
                                onClick = { onProductionCountryClick.invoke(info.productionCountries.first()) }
                            )
                            .padding(8.dp),
                        style = TextStyles.Info,
                        textAlign = TextAlign.Center,
                        text = info.productionCountries.first().name
                    )
                }
            }
        }
    }
}

private fun Int?.asMovieRuntime(): String = if (this != null) {
    val minuteFormat = SimpleDateFormat("mm", Locale.ENGLISH)
    val hourFormat = SimpleDateFormat("H:mm", Locale.ENGLISH)
    hourFormat.format(minuteFormat.parse(this.toString()) as Date)
} else {
    ""
}

@Preview
@Composable
private fun MovieDetailsInfoUIPreview() {
    MovieDetailsInfoUI(
        info = UIMovieInfo.Default,
        posterPath = "",
        onProductionCountryClick = {}
    )
}
