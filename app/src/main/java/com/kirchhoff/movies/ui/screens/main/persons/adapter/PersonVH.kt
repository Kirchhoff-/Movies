package com.kirchhoff.movies.ui.screens.main.persons.adapter

import android.view.ViewGroup
import android.widget.ImageView
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.Person
import com.kirchhoff.movies.extensions.downloadAvatar
import com.kirchhoff.movies.extensions.inflate
import com.kirchhoff.movies.ui.utils.recyclerView.BaseVH

class PersonVH(parent: ViewGroup) : BaseVH<Person>(parent.inflate(R.layout.item_person)) {

    private val ivAvatar: ImageView = itemView.findViewById(R.id.ivAvatar)

    override fun bind(item: Person) {
        ivAvatar.downloadAvatar(item.profile_path)
    }
}
