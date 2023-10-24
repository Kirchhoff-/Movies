package com.kirchhoff.movies.screen.person.ui.screen.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.kirchhoff.movies.core.ui.BaseFragment

internal class PersonImagesFragment : BaseFragment() {

    private val imagesUrls by lazy {
        requireArguments().getStringArrayList(IMAGES_URLS_ARG)
            ?: error("Should provide images urls argument")
    }

    private val startPosition by lazy {
        requireArguments().getInt(START_POSITION_ARG)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            PersonImagesUI(
                imagesUrls = imagesUrls,
                startPosition = startPosition
            ) {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    companion object {
        fun newInstance(imagesUrls: List<String>, startPosition: Int): PersonImagesFragment =
            PersonImagesFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList(IMAGES_URLS_ARG, ArrayList(imagesUrls))
                    putInt(START_POSITION_ARG, startPosition)
                }
            }

        private const val IMAGES_URLS_ARG = "IMAGES_URLS_ARG"
        private const val START_POSITION_ARG = "START_POSITION_ARG"
    }
}
