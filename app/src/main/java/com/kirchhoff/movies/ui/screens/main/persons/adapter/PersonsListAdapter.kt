package com.kirchhoff.movies.ui.screens.main.persons.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.core.ui.recyclerview.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.BaseVH
import com.kirchhoff.movies.data.ui.main.UIPerson

class PersonsListAdapter(clickListener: OnItemClickListener<UIPerson>) :
    BaseRecyclerViewAdapter<BaseVH<UIPerson>, UIPerson>(itemClickListener = clickListener) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PersonVH(parent)
}
