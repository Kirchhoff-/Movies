package com.kirchhoff.movies.screen.person.router

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.core.R
import com.kirchhoff.movies.screen.person.ui.screen.images.PersonImagesFragment

class PersonRouter(private val activity: AppCompatActivity) : IPersonRouter {
    override fun openImagesScreen(imagesUrls: List<String>, currentPosition: Int) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, PersonImagesFragment.newInstance(imagesUrls, currentPosition))
            .addToBackStack(null)
            .commit()
    }
}
