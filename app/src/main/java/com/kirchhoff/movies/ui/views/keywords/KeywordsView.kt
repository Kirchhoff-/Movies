package com.kirchhoff.movies.ui.views.keywords

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.res.use
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.kirchhoff.movies.R
import com.kirchhoff.movies.ui.views.keywords.adapter.KeywordsListAdapter

class KeywordsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val keywordAdapter = KeywordsListAdapter()

    init {
        var firstItemMarginEnabled: Boolean = false
        context.theme.obtainStyledAttributes(attrs, R.styleable.KeywordsView, 0, 0).use {
            firstItemMarginEnabled = it.getBoolean(R.styleable.KeywordsView_firstItemMarginEnabled, false)
        }

        val layoutMng = FlexboxLayoutManager(context).apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.FLEX_START
        }

        addItemDecoration(
            KeywordsItemDecoration(
                resources.getDimensionPixelSize(R.dimen.keyword_item_top_margin),
                resources.getDimensionPixelSize(R.dimen.keyword_item_bottom_margin),
                resources.getDimensionPixelSize(R.dimen.keyword_item_edges_margin),
                firstItemMarginEnabled
            )
        )

        layoutManager = layoutMng
        adapter = keywordAdapter
    }

    fun displayItems(items: List<String>) {
        keywordAdapter.addItems(items)
    }
}
