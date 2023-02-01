package com.kirchhoff.movies.core.ui

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {
    protected fun <T> LiveData<T>.subscribe(func: (T) -> Unit) {
        this.observe(viewLifecycleOwner, Observer { func(it) })
    }
}
