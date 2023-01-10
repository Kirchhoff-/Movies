package com.kirchhoff.movies.ui.screens.core.credits.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.core.ui.recyclerview.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.BaseVH
import com.kirchhoff.movies.ui.screens.core.credits.CreditsView

class CreditsAdapter(itemClickListener: OnItemClickListener<CreditsView.CreditsInfo>) :
    BaseRecyclerViewAdapter<BaseVH<CreditsView.CreditsInfo>, CreditsView.CreditsInfo>(itemClickListener = itemClickListener) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseVH<CreditsView.CreditsInfo> = CreditVH(parent)
}
