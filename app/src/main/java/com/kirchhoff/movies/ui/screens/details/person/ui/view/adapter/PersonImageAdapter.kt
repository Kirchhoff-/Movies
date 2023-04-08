package com.kirchhoff.movies.ui.screens.details.person.ui.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PersonImageAdapter(
    fragment: Fragment,
    private val imagesUrls: List<String>,
    private val imageClickListener: (() -> Unit)?
) : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment =
        PersonImageFragment.newInstance(imagesUrls[position]).apply {
            imageClickListener(imageClickListener)
        }

    override fun getItemCount(): Int = imagesUrls.size
}
