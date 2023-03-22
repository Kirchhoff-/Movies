package com.kirchhoff.movies.core.ui

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

abstract class BaseFragment : Fragment {
    constructor() : super()
    constructor(@LayoutRes layoutId: Int) : super(layoutId)

    protected fun <T> LiveData<T>.subscribe(func: (T) -> Unit) {
        this.observe(viewLifecycleOwner) { func(it) }
    }
}
