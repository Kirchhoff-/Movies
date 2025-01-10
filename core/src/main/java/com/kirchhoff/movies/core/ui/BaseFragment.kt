package com.kirchhoff.movies.core.ui

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.kirchhoff.movies.core.router.IRouter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

@Suppress("UnnecessaryAbstractClass")
abstract class BaseFragment : Fragment {
    constructor() : super()
    constructor(@LayoutRes layoutId: Int) : super(layoutId)

    protected val router: IRouter by inject { parametersOf(requireActivity()) }
}
