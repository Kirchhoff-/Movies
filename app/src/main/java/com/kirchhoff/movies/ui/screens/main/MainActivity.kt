package com.kirchhoff.movies.ui.screens.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.core.router.IRouter
import com.kirchhoff.movies.core.ui.utils.viewBinding
import com.kirchhoff.movies.databinding.ActivityWithFragmentBinding
import com.kirchhoff.movies.router.routerModule
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {

    private val viewBinding by viewBinding(ActivityWithFragmentBinding::inflate)

    private val router: IRouter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(routerModule)
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        router.openDiscoverScreen()
    }
}
