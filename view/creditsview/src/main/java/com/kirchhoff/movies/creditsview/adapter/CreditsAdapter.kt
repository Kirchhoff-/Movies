package com.kirchhoff.movies.creditsview.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.adapter.viewholder.BaseVH
import com.kirchhoff.movies.creditsview.CreditsView

class CreditsAdapter(itemClickListener: OnItemClickListener<CreditsView.CreditsInfo>) :
    BaseRecyclerViewAdapter<BaseVH<CreditsView.CreditsInfo>, CreditsView.CreditsInfo>(itemClickListener = itemClickListener) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseVH<CreditsView.CreditsInfo> = CreditVH(parent)
}
