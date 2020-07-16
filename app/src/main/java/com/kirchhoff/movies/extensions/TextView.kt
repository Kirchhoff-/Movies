package com.kirchhoff.movies.extensions

import android.widget.TextView
import androidx.core.view.isVisible

fun TextView.setTextOrGone(txt: String?) {
    isVisible = txt != null
    text = txt
}

fun TextView.setTextOrGone(txt: Int?, formattedText: String) {
    isVisible = txt != null
    text = formattedText
}
