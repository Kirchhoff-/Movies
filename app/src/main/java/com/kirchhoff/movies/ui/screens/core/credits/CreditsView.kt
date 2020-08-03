package com.kirchhoff.movies.ui.screens.core.credits

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kirchhoff.movies.R
import com.kirchhoff.movies.data.responses.CreditsInfo
import com.kirchhoff.movies.ui.screens.core.credits.adapter.CreditsAdapter
import com.kirchhoff.movies.ui.utils.recyclerView.decorations.EdgesMarginItemDecoration
import com.kirchhoff.movies.ui.utils.recyclerView.decorations.TopBottomMarginItemDecoration

class CreditsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val castAdapter = CreditsAdapter()
    private val crewAdapter = CreditsAdapter()

    private val tvCastCredits: TextView
    private val tvCrewCredits: TextView
    private val rvCastCredits: RecyclerView
    private val rvCrewCredits: RecyclerView

    init {
        LayoutInflater.from(context).inflate(R.layout.view_credits, this)

        tvCastCredits = findViewById(R.id.tvCastCredits)
        tvCrewCredits = findViewById(R.id.tvCrewCredits)
        rvCastCredits = findViewById(R.id.rvCastCredits)
        rvCrewCredits = findViewById(R.id.rvCrewCredits)

        configRecyclerView(rvCastCredits, castAdapter)
        configRecyclerView(rvCrewCredits, crewAdapter)
    }

    fun displayItems(castCredits: List<CreditsInfo>?, crewCredits: List<CreditsInfo>?) {
        if (!castCredits.isNullOrEmpty()) {
            tvCastCredits.isVisible = true
            rvCastCredits.isVisible = true
            castAdapter.addItems(castCredits)
        }

        if (!crewCredits.isNullOrEmpty()) {
            tvCrewCredits.isVisible = true
            rvCrewCredits.isVisible = true
            crewAdapter.addItems(crewCredits)
        }
    }

    private fun configRecyclerView(recyclerView: RecyclerView, creditsAdapter: RecyclerView.Adapter<*>) {
        recyclerView.apply {
            adapter = creditsAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            addItemDecoration(EdgesMarginItemDecoration(resources.getDimensionPixelSize(R.dimen.credits_view_edges_margin)))
            addItemDecoration(
                TopBottomMarginItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.credits_view_edges_margin),
                    resources.getDimensionPixelSize(R.dimen.credits_view_edges_margin)
                )
            )
            isNestedScrollingEnabled = false
        }
    }
}
