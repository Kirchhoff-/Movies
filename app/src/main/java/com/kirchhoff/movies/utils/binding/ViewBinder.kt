package com.kirchhoff.movies.utils.binding

import android.view.View
import androidx.viewbinding.ViewBinding

/**
 * Create instance of [ViewBinding] from a [View]
 */
interface ViewBinder<T : ViewBinding> {
    fun bind(view: View): T
}

@PublishedApi
internal inline fun <T : ViewBinding> viewBinder(crossinline bindView: (View) -> T): ViewBinder<T> =
    object : ViewBinder<T> {
        override fun bind(view: View) = bindView(view)
    }
