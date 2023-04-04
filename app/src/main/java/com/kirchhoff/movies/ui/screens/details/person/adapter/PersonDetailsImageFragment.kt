package com.kirchhoff.movies.ui.screens.details.person.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kirchhoff.movies.core.extensions.downloadPoster
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.databinding.FragmentPersonDetailsImageBinding

class PersonDetailsImageFragment : BaseFragment() {

    private var _viewBinding: FragmentPersonDetailsImageBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val imageUrl by lazy {
        requireArguments().getString(IMAGE_ARG) ?: error("Should provide image url argument")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentPersonDetailsImageBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.ivPersonImage.downloadPoster(imageUrl)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    companion object {
        fun newInstance(imageUrl: String): PersonDetailsImageFragment =
            PersonDetailsImageFragment().apply {
                arguments = Bundle().apply {
                    putString(IMAGE_ARG, imageUrl)
                }
            }

        private const val IMAGE_ARG = "IMAGE_ARG"
    }
}
