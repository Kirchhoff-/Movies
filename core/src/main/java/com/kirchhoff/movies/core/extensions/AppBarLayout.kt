package com.kirchhoff.movies.core.extensions

import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

fun AppBarLayout.addTitleWithCollapsingListener(
    toolbar: Toolbar,
    title: String
) {
    addOnOffsetChangedListener(
        AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
           toolbar.title = if (abs(verticalOffset) - totalScrollRange == 0) title else ""
        }
    )
}
