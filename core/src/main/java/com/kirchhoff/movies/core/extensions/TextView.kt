package com.kirchhoff.movies.core.extensions

import android.widget.TextView
import androidx.core.view.isVisible

fun TextView.setTextOrGone(txt: String?) {
    isVisible = txt != null
    text = txt
}
