package com.kirchhoff.movies.core.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity

@Composable
fun Int.pxToDp() = with(LocalDensity.current) { this@pxToDp.toDp() }
