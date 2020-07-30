package com.kirchhoff.movies.ui.screens.details.person.views.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.data.responses.PersonCredit
import com.kirchhoff.movies.ui.utils.recyclerView.BaseRecyclerViewAdapter
import com.kirchhoff.movies.ui.utils.recyclerView.BaseVH

class PersonCreditAdapter : BaseRecyclerViewAdapter<BaseVH<PersonCredit>, PersonCredit>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH<PersonCredit> =
        PersonCreditVH(
            parent
        )
}
