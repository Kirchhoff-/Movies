package com.kirchhoff.movies.ui.screens.core.credit

import android.view.ViewGroup
import com.kirchhoff.movies.data.responses.CreditsInfo
import com.kirchhoff.movies.ui.utils.recyclerView.BaseRecyclerViewAdapter
import com.kirchhoff.movies.ui.utils.recyclerView.BaseVH

class CreditAdapter : BaseRecyclerViewAdapter<BaseVH<CreditsInfo>, CreditsInfo>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH<CreditsInfo> =
        CreditVH(
            parent
        )
}
