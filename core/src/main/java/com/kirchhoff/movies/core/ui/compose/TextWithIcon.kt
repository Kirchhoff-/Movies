@file:SuppressWarnings("MagicNumber")

package com.kirchhoff.movies.core.ui.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextWithIcon(
    imageVector: ImageVector,
    text: String,
    space: Dp = 4.dp,
    style: TextStyle = textStyle
) {
    TextWithIcon(
        painter = rememberVectorPainter(imageVector),
        text = text,
        space = space,
        style = style
    )
}

@Composable
fun TextWithIcon(
    painter: Painter,
    text: String,
    space: Dp = 4.dp,
    style: TextStyle = textStyle
) {
    Icon(
        painter = painter,
        contentDescription = ""
    )
    Spacer(modifier = Modifier.width(space))
    Text(
        style = style,
        text = text
    )
}

private val textStyle: TextStyle = TextStyle(
    fontSize = 14.sp,
    color = Color.Black
)

@Preview
@Composable
private fun TextWithIconPreview() {
    TextWithIcon(
        imageVector = Icons.Filled.ArrowBack,
        text = "Some text",
        space = 4.dp,
        style = textStyle
    )
}
