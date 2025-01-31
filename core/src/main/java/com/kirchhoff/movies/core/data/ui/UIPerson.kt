package com.kirchhoff.movies.core.data.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIPerson(
    val id: Int,
    val name: String,
    val profilePath: String?
) : Parcelable {
    constructor(person: UIEntertainmentPerson) : this(person.id, person.name, person.profilePath)

    companion object {
        val Default = UIPerson(
            id = 0,
            name = "",
            profilePath = ""
        )
    }
}
