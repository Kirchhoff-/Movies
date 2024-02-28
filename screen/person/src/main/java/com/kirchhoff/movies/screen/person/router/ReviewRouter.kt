package com.kirchhoff.movies.screen.person.router

import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.core.R
import com.kirchhoff.movies.screen.person.ui.screen.images.PersonImagesFragment

internal interface IPersonRouter {
    fun openImagesScreen(personId: Int, currentPosition: Int)
}

internal class PersonRouter(private val activity: AppCompatActivity) : IPersonRouter {
    override fun openImagesScreen(personId: Int, currentPosition: Int) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, PersonImagesFragment.newInstance(personId, currentPosition))
            .addToBackStack(null)
            .commit()
    }
}
