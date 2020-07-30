package com.kirchhoff.movies.ui.screens.details.person.views.adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.responses.PersonCredit
import com.kirchhoff.movies.extensions.downloadPoster
import com.kirchhoff.movies.extensions.inflate
import com.kirchhoff.movies.ui.utils.recyclerView.BaseVH

class PersonCreditVH(parent: ViewGroup) : BaseVH<PersonCredit>(parent.inflate(R.layout.item_person_credit)) {

    private val ivCreditPoster: ImageView = itemView.findViewById(R.id.ivCreditPoster)
    private val tvCreditName: TextView = itemView.findViewById(R.id.tvCreditName)
    private val tvCharacter: TextView = itemView.findViewById(R.id.tvCharacter)

    override fun bind(item: PersonCredit) {
        ivCreditPoster.downloadPoster(item.poster_path)
        tvCreditName.text = item.title
        tvCharacter.text = item.character
    }
}
