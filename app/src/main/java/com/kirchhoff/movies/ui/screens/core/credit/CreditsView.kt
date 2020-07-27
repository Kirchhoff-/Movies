package com.kirchhoff.movies.ui.screens.core.credit

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.responses.CreditsInfo
import com.kirchhoff.movies.ui.screens.core.credit.adapter.CreditsAdapter
import com.kirchhoff.movies.ui.utils.recyclerView.decorations.EdgesMarginItemDecoration
import com.kirchhoff.movies.ui.utils.recyclerView.decorations.TopBottomMarginItemDecoration

class CreditsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val creditsAdapter =
        CreditsAdapter()

    init {
        adapter = creditsAdapter
        layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
        addItemDecoration(EdgesMarginItemDecoration(resources.getDimensionPixelSize(R.dimen.person_details_credits_margin)))
        addItemDecoration(
            TopBottomMarginItemDecoration(
                resources.getDimensionPixelSize(R.dimen.person_details_credits_margin),
                resources.getDimensionPixelSize(R.dimen.person_details_credits_margin)
            )
        )
    }

    fun displayItems(items: List<CreditsInfo>) {
        creditsAdapter.addItems(items)
    }
}
