package com.kirchhoff.movies.ui.screens.details.fake

import android.os.Bundle
import android.view.View
import com.kirchhoff.movies.R
import com.kirchhoff.movies.databinding.FragmentFakeBinding
import com.kirchhoff.movies.databinding.FragmentMovieDetailsBinding
import com.kirchhoff.movies.ui.screens.BaseFragment
import com.kirchhoff.movies.utils.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel

class FakeFragment: BaseFragment(R.layout.fragment_fake) {

    private val vm by viewModel<FakeVM>()
    private val viewBinding by viewBinding(FragmentFakeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            button1.setOnClickListener { vm.first() }
            button2.setOnClickListener { vm.second() }
            button3.setOnClickListener { vm.third() }
            button4.setOnClickListener { vm.example1() }
            button5.setOnClickListener { vm.example2() }
            button6.setOnClickListener { vm.example3() }
            button7.setOnClickListener { vm.example4() }
            button8.setOnClickListener { vm.example5() }
            button9.setOnClickListener { vm.example7() }
        }
    }
}