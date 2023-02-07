package com.kirchhoff.movies.creditsview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kirchhoff.movies.core.ui.recyclerview.BaseRecyclerViewAdapter
import com.kirchhoff.movies.core.ui.recyclerview.decorations.EdgesMarginItemDecoration
import com.kirchhoff.movies.core.ui.recyclerview.decorations.TopBottomMarginItemDecoration
import com.kirchhoff.movies.creditsview.adapter.CreditsAdapter
import com.kirchhoff.movies.creditsview.databinding.ViewCreditsBinding

class CreditsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    interface CreditsInfo {
        fun title(): String?
        fun description(): String?
        fun imagePath(): String?
    }

    private val castAdapter = CreditsAdapter(CastCreditsClickListener())
    private val crewAdapter = CreditsAdapter(CrewCreditsClickListener())

    private val binding = ViewCreditsBinding.inflate(LayoutInflater.from(context), this, true)

    private var castClickListener: ((CreditsInfo) -> Unit)? = null
    private var crewClickListener: ((CreditsInfo) -> Unit)? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_credits, this)
        configRecyclerView(binding.rvCastCredits, castAdapter)
        configRecyclerView(binding.rvCrewCredits, crewAdapter)
    }

    fun displayItems(castCredits: List<CreditsInfo>?, crewCredits: List<CreditsInfo>?) {
        with(binding) {
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
    }

    fun setCastClickListener(castClickListener: (CreditsInfo) -> Unit) {
        this.castClickListener = castClickListener
    }

    fun setCrewClickListener(crewClickListener: (CreditsInfo) -> Unit) {
        this.crewClickListener = crewClickListener
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
            castClickListener?.invoke(item)
        }
    }

    private inner class CrewCreditsClickListener : BaseRecyclerViewAdapter.OnItemClickListener<CreditsInfo> {
        override fun onItemClick(item: CreditsInfo) {
            crewClickListener?.invoke(item)
        }
    }
}
