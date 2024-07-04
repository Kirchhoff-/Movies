package com.kirchhoff.movies.core.ui.compose

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.kirchhoff.movies.core.ui.resources.Colors

@Composable
fun MoviesToolbar(
    title: String,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Colors.White
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackPressed.invoke() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    tint = Colors.White,
                    contentDescription = ""
                )
            }
        },
        backgroundColor = Colors.Primary
    )
}
