package com.kirchhoff.movies.ui.screens.main.persons.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.data.Person
import com.kirchhoff.movies.ui.utils.recyclerView.BaseRecyclerViewAdapter
import com.kirchhoff.movies.ui.utils.recyclerView.BaseVH

class PersonsListAdapter : BaseRecyclerViewAdapter<BaseVH<Person>, Person>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PersonVH(parent)
}
