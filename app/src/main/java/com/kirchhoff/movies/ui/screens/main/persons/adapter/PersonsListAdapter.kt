package com.kirchhoff.movies.ui.screens.main.persons.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.core.data.UIPerson
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.adapter.viewholder.BaseVH

class PersonsListAdapter(clickListener: OnItemClickListener<UIPerson>) :
    BaseRecyclerViewAdapter<BaseVH<UIPerson>, UIPerson>(itemClickListener = clickListener) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PersonVH(parent)
}
