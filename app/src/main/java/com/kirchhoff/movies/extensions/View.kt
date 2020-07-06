package com.kirchhoff.movies.extensions

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

var View.visible
    get() = visibility == VISIBLE
    set(value) {
        visibility = if (value) VISIBLE else GONE
    }
