package com.kirchhoff.movies.creditsview.adapter

import android.view.ViewGroup
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.adapter.viewholder.BaseVH
import com.kirchhoff.movies.creditsview.data.CreditsInfo

class CreditsAdapter(itemClickListener: OnItemClickListener<CreditsInfo>) :
    BaseRecyclerViewAdapter<BaseVH<CreditsInfo>, CreditsInfo>(itemClickListener = itemClickListener) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseVH<CreditsInfo> = CreditVH(parent)
}
