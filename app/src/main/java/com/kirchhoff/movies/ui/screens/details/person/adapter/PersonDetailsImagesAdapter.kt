package com.kirchhoff.movies.ui.screens.details.person.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kirchhoff.movies.data.ui.details.person.UIPersonImage

class PersonDetailsImagesAdapter(
    fragment: Fragment,
    private val images: List<UIPersonImage>
) : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment =
        PersonDetailsImageFragment.newInstance(images[position].url)

    override fun getItemCount(): Int = images.size
}
