package com.kirchhoff.movies.keywordsview

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.res.use
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.kirchhoff.movies.core.ui.recyclerview.adapter.BaseRecyclerViewAdapter
import com.kirchhoff.movies.keywordsview.adapter.KeywordsListAdapter
import com.kirchhoff.movies.keywordsview.data.KeywordsViewData
import com.kirchhoff.movies.keywordsview.decorations.KeywordsItemDecoration

class KeywordsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val keywordAdapter = KeywordsListAdapter(KeywordClickListener())

    private var keywordItemClickListener: ((KeywordsViewData) -> Unit)? = null

    init {
        var firstItemMarginEnabled = false
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

    fun displayItems(items: List<KeywordsViewData>) {
        keywordAdapter.addItems(items)
    }

    @Suppress("unused")
    fun itemClickListener(listener: (KeywordsViewData) -> Unit) {
        keywordItemClickListener = listener
    }

    private inner class KeywordClickListener : BaseRecyclerViewAdapter.OnItemClickListener<KeywordsViewData> {
        override fun onItemClick(item: KeywordsViewData) {
            keywordItemClickListener?.invoke(item)
        }
    }
}
