package com.kirchhoff.movies.screen.person.ui.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kirchhoff.movies.core.extensions.downloadPoster
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.screen.person.databinding.FragmentPersonImageBinding

internal class PersonImageFragment : BaseFragment() {

    private var _viewBinding: FragmentPersonImageBinding? = null
    private val viewBinding get() = _viewBinding!!

    private var imageClickListener: (() -> Unit)? = null

    private val imageUrl by lazy {
        requireArguments().getString(IMAGE_ARG) ?: error("Should provide image url argument")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentPersonImageBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding.ivPersonImage) {
            downloadPoster(imageUrl)
            setOnClickListener {
                imageClickListener?.invoke()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    fun imageClickListener(listener: (() -> Unit)?) {
        imageClickListener = listener
    }

    companion object {
        fun newInstance(imageUrl: String): PersonImageFragment =
            PersonImageFragment().apply {
                arguments = Bundle().apply {
                    putString(IMAGE_ARG, imageUrl)
                }
            }

        private const val IMAGE_ARG = "IMAGE_ARG"
    }
}
