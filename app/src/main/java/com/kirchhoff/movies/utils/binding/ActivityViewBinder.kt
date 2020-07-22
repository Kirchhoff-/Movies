package com.kirchhoff.movies.utils.binding

import android.app.Activity
import android.view.View
import androidx.viewbinding.ViewBinding

@PublishedApi
internal class ActivityViewBinder<T : ViewBinding>(
    private val viewBindingClass: Class<T>,
    private val viewProvider: (Activity) -> View
) {

    /**
     * Cache static method `ViewBinding.bind(View)`
     */
    private val bindViewMethod by lazy(LazyThreadSafetyMode.NONE) {
        viewBindingClass.getMethod("bind", View::class.java)
    }

    /**
     * Create new [ViewBinding] instance
     */
    @Suppress("UNCHECKED_CAST")
    fun bind(activity: Activity): T {
        val view = viewProvider(activity)
        return bindViewMethod(null, view) as T
    }
}
