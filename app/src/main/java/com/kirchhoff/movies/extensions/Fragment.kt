package com.kirchhoff.movies.extensions

import androidx.annotation.DimenRes
import androidx.fragment.app.Fragment

fun Fragment.getSizeFromRes(@DimenRes size: Int) = resources.getDimensionPixelSize(size)
