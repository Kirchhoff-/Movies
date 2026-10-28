package com.kirchhoff.movies.ui.screens.main

import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.kirchhoff.movies.core.R
import com.kirchhoff.movies.core.ui.utils.viewBinding
import com.kirchhoff.movies.databinding.ActivityWithFragmentBinding
import com.kirchhoff.movies.router.routerModule
import com.kirchhoff.movies.ui.screens.main.router.DashboardRouter
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {

    private val viewBinding by viewBinding(ActivityWithFragmentBinding::inflate)

    private val dashboardRouter: DashboardRouter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(routerModule)
        loadKoinModules(dashboardModule)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(getColor(R.color.colorPrimaryDark)),
            navigationBarStyle = SystemBarStyle.dark(getColor(android.R.color.black))
        )
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(viewBinding.root) { view, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )
            view.updatePadding(
                left = bars.left,
                top = bars.top,
                right = bars.right,
                bottom = bars.bottom
            )
            WindowInsetsCompat.CONSUMED
        }
        ViewCompat.requestApplyInsets(viewBinding.root)
        dashboardRouter.openDashboard()
    }
}
