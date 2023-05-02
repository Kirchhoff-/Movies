package com.kirchhoff.movies.core.ui

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.kirchhoff.movies.core.router.IRouter
import org.koin.android.ext.android.inject

abstract class BaseFragment : Fragment {
    constructor() : super()
    constructor(@LayoutRes layoutId: Int) : super(layoutId)

    protected val router: IRouter by inject()

    protected fun <T> LiveData<T>.subscribe(func: (T) -> Unit) {
        this.observe(viewLifecycleOwner) { func(it) }
    }
}
