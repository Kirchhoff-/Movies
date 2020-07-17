package com.kirchhoff.movies.ui.screens.details.tv

import android.os.Bundle
import android.view.View
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.Tv
import com.kirchhoff.movies.databinding.FragmentTvDetailsBinding
import com.kirchhoff.movies.ui.screens.BaseFragment
import com.kirchhoff.movies.utils.binding.viewBinding

class TvDetailsFragment : BaseFragment(R.layout.fragment_tv_details) {

    private val tv: Tv by lazy { arguments!!.getParcelable<Tv>(TV_ARG)!! }

    private val viewBinding: FragmentTvDetailsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.tvTitle.text = tv.name
    }

    companion object {
        fun newInstance(tv: Tv): TvDetailsFragment {
            val fragment = TvDetailsFragment()
            val arg = Bundle()
            arg.putParcelable(TV_ARG, tv)
            fragment.arguments = arg
            return fragment
        }

        private const val TV_ARG = "TV_ARG"
    }
}