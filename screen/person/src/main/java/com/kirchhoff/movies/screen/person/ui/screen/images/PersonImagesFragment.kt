package com.kirchhoff.movies.screen.person.ui.screen.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.kirchhoff.movies.core.ui.BaseFragment
import com.kirchhoff.movies.screen.person.R
import com.kirchhoff.movies.screen.person.databinding.FragmentPersonImagesBinding
import com.kirchhoff.movies.screen.person.ui.view.adapter.PersonImageAdapter

class PersonImagesFragment : BaseFragment() {

    private var _viewBinding: FragmentPersonImagesBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val imagesUrls by lazy {
        requireArguments().getStringArrayList(IMAGES_URLS_ARG)
            ?: error("Should provide images urls argument")
    }

    private val currentPosition by lazy {
        requireArguments().getInt(CURRENT_POSITION_ARG)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentPersonImagesBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding) {
            vpImages.apply {
                adapter = PersonImageAdapter(
                    fragment = this@PersonImagesFragment,
                    imagesUrls = imagesUrls,
                    imageClickListener = null
                )
                registerOnPageChangeCallback(PageChangeListener())
                setCurrentItem(currentPosition, false)
            }

            toolbar.setNavigationOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    override fun onDestroyView() {
        viewBinding.vpImages.adapter = null
        super.onDestroyView()
        _viewBinding = null
    }

    private inner class PageChangeListener : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            viewBinding.toolbar.title = getString(
                R.string.person_images_title_format,
                position + 1,
                imagesUrls.size
            )
        }
    }

    companion object {
        fun newInstance(imagesUrls: List<String>, currentPosition: Int): PersonImagesFragment =
            PersonImagesFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList(IMAGES_URLS_ARG, ArrayList(imagesUrls))
                    putInt(CURRENT_POSITION_ARG, currentPosition)
                }
            }

        private const val IMAGES_URLS_ARG = "IMAGES_URLS_ARG"
        private const val CURRENT_POSITION_ARG = "CURRENT_POSITION_ARG"
    }
}
