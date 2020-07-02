package com.kirchhoff.movies.utils.binding

import android.os.Looper

internal fun checkIsMainThread() = check(isMainThread)

internal inline val isMainThread: Boolean
    get() = Looper.myLooper() == Looper.getMainLooper()
