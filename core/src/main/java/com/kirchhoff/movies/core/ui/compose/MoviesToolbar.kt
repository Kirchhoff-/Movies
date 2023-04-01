package com.kirchhoff.movies.core.ui.compose

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.kirchhoff.movies.core.R

@Composable
fun MoviesToolbar(
    title: String,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = colorResource(R.color.white)
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackPressed.invoke() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    tint = colorResource(R.color.white),
                    contentDescription = ""
                )
            }
        },
        backgroundColor = colorResource(R.color.colorPrimary)
    )
}
