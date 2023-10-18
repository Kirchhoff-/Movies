package com.kirchhoff.movies.screen.movie.ui.screen.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.screen.movie.ui.screen.image.ui.MovieImageUI

internal class MovieImageFragment : BaseFragment() {

    private val imagePath: String by lazy {
        requireArguments().getString(IMAGE_PATH_ARG)
            ?: error("Should provide argument for fragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            MovieImageUI(
                imagePath = imagePath,
                onBackPressed = { requireActivity().onBackPressedDispatcher.onBackPressed() }
            )
        }
    }

    companion object {
        fun newInstance(imagePath: String): MovieImageFragment =
            MovieImageFragment().apply {
                arguments = Bundle().apply {
                    putString(IMAGE_PATH_ARG, imagePath)
                }
            }

        private const val IMAGE_PATH_ARG = "IMAGE_PATH_ARG"
    }
}
