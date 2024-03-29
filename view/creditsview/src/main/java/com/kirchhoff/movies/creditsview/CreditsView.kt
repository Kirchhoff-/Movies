package com.kirchhoff.movies.creditsview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kirchhoff.movies.core.data.UIEntertainmentCredits
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.decorations.EdgesMarginItemDecoration
import com.kirchhoff.movies.core.ui.recyclerview.decorations.TopBottomMarginItemDecoration
import com.kirchhoff.movies.creditsview.adapter.CreditsAdapter
import com.kirchhoff.movies.creditsview.data.CreditsInfo
import com.kirchhoff.movies.creditsview.databinding.ViewCreditsBinding

class CreditsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val castAdapter = CreditsAdapter(CastCreditsClickListener())
    private val crewAdapter = CreditsAdapter(CrewCreditsClickListener())

    private val binding = ViewCreditsBinding.inflate(LayoutInflater.from(context), this, true)

    private var creditItemClickListener: ((Int) -> Unit)? = null
    private var castSeeAllClickListener: (() -> Unit)? = null
    private var crewSeeAllClickListener: (() -> Unit)? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_credits, this)
        configRecyclerView(binding.rvCastCredits, castAdapter)
        configRecyclerView(binding.rvCrewCredits, crewAdapter)
    }

    fun display(
        castCredits: List<CreditsInfo>?,
        crewCredits: List<CreditsInfo>?,
        seeAllVisible: Boolean
    ) {
        displayCredits(
            castCredits,
            crewCredits,
            seeAllVisible
        )
    }

    fun display(
        credits: UIEntertainmentCredits,
        seeAllVisible: Boolean
    ) {
        displayCredits(
            credits.cast?.map { CreditsInfo(it) },
            credits.crew?.map { CreditsInfo(it) },
            seeAllVisible
        )
    }

    fun itemClickListener(listener: (Int) -> Unit) {
        creditItemClickListener = listener
    }

    fun castSeeAllClickListener(listener: () -> Unit) {
        castSeeAllClickListener = listener
    }

    fun crewSeeAllClickListener(listener: () -> Unit) {
        crewSeeAllClickListener = listener
    }

    private fun displayCredits(
        castCredits: List<CreditsInfo>?,
        crewCredits: List<CreditsInfo>?,
        seeAllVisible: Boolean
    ) {
        with(binding) {
            val castCreditsVisible = !castCredits.isNullOrEmpty()
            tvCastCredits.isVisible = castCreditsVisible
            rvCastCredits.isVisible = castCreditsVisible
            castCredits?.let { castAdapter.addItems(it) }
            tvSeeAllCastCredits.isVisible = seeAllVisible && castCreditsVisible
            tvSeeAllCastCredits.setOnClickListener { castSeeAllClickListener?.invoke() }

            val crewCreditsVisible = !crewCredits.isNullOrEmpty()
            tvCrewCredits.isVisible = crewCreditsVisible
            rvCrewCredits.isVisible = crewCreditsVisible
            crewCredits?.let { crewAdapter.addItems(it) }
            tvSeeAllCrewCredits.isVisible = seeAllVisible && crewCreditsVisible
            tvSeeAllCrewCredits.setOnClickListener { crewSeeAllClickListener?.invoke() }
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

    private inner class CastCreditsClickListener : BaseRecyclerViewAdapter.OnItemClickListener<CreditsInfo> {
        override fun onItemClick(item: CreditsInfo) {
            creditItemClickListener?.invoke(item.id)
        }
    }

    private inner class CrewCreditsClickListener : BaseRecyclerViewAdapter.OnItemClickListener<CreditsInfo> {
        override fun onItemClick(item: CreditsInfo) {
            creditItemClickListener?.invoke(item.id)
        }
    }
}
