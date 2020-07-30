package com.kirchhoff.movies.ui.screens.details.person.views

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.responses.PersonCredit
import com.kirchhoff.movies.ui.screens.details.person.views.adapter.PersonCreditAdapter
import com.kirchhoff.movies.ui.utils.recyclerView.decorations.EdgesMarginItemDecoration
import com.kirchhoff.movies.ui.utils.recyclerView.decorations.TopBottomMarginItemDecoration

class PersonCreditsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val creditsAdapter = PersonCreditAdapter()

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

    fun displayItems(items: List<PersonCredit>) {
        creditsAdapter.addItems(items)
    }
}
