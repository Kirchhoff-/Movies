package com.kirchhoff.movies.ui.screens.main

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.kirchhoff.movies.R
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.core.ui.utils.viewBinding
import com.kirchhoff.movies.databinding.FragmentMainBinding
import com.kirchhoff.movies.ui.screens.main.router.IDashboardRouter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

internal class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val viewBinding by viewBinding(FragmentMainBinding::bind)

    private val dashboardRouter: IDashboardRouter by inject { parametersOf(requireActivity()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createUI()
    }

    private fun createUI() {
        with(viewBinding) {
            vpMain.apply {
                adapter = MainPagerAdapter(childFragmentManager, dashboardRouter)
                offscreenPageLimit = PAGE_COUNT
                addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
                    override fun onPageSelected(position: Int) {
                        navigationView.menu.getItem(position).isChecked = true
                    }
                })
            }

            navigationView.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.item_movies -> vpMain.currentItem = 0
                    R.id.item_tv -> vpMain.currentItem = 1
                    R.id.item_persons -> vpMain.currentItem = 2
                }
                true
            }
        }
    }

    companion object {
        private const val PAGE_COUNT = 3

        fun newInstance(): MainFragment = MainFragment()
    }
}
