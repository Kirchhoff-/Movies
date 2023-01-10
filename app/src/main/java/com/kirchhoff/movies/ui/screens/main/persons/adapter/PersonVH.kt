package com.kirchhoff.movies.ui.screens.main.persons.adapter

import android.view.ViewGroup
import android.widget.ImageView
import com.kirchhoff.movies.R
import com.kirchhoff.movies.core.extensions.downloadAvatar
import com.kirchhoff.movies.core.extensions.inflate
import com.kirchhoff.movies.core.ui.recyclerview.BaseVH
import com.kirchhoff.movies.data.ui.main.UIPerson

class PersonVH(parent: ViewGroup) : BaseVH<UIPerson>(parent.inflate(R.layout.item_person)) {

    private val ivAvatar: ImageView = itemView.findViewById(R.id.ivAvatar)

    override fun bind(item: UIPerson) {
        ivAvatar.downloadAvatar(item.profilePath)
    }
}
