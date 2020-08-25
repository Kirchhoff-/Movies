package com.kirchhoff.movies.ui.screens.core.credits.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.ui.screens.core.credits.CreditsView
import com.kirchhoff.movies.ui.utils.recyclerView.BaseRecyclerViewAdapter
import com.kirchhoff.movies.ui.utils.recyclerView.BaseVH

class CreditsAdapter(itemClickListener: OnItemClickListener<CreditsView.CreditsInfo>) :
    BaseRecyclerViewAdapter<BaseVH<CreditsView.CreditsInfo>, CreditsView.CreditsInfo>(itemClickListener = itemClickListener) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseVH<CreditsView.CreditsInfo> = CreditVH(parent)
}
